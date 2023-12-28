package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.configuration.PropertiesConfig;
import com.api.telisadoptproyect.api.configuration.SendgridEmailSender;
import com.api.telisadoptproyect.api.request.LoginRequest;
import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerLoginRequest;
import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerRequest;
import com.api.telisadoptproyect.api.request.ResetPasswordRequest;
import com.api.telisadoptproyect.api.response.AuthenticationResponses.AuthenticationResponse;
import com.api.telisadoptproyect.api.response.AuthenticationResponses.JwtResponse;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.security.JwtProvider;
import com.api.telisadoptproyect.api.validation.InputValidation;
import com.api.telisadoptproyect.api.validation.OwnerValidation;
import com.api.telisadoptproyect.library.entity.Owner;
import com.api.telisadoptproyect.library.entity.OwnerOtp;
import com.api.telisadoptproyect.library.entity.PasswordResetToken;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.OwnerOtpRepository;
import com.api.telisadoptproyect.library.util.EmailStructure;
import com.api.telisadoptproyect.library.util.EmailStructureUtils;
import com.api.telisadoptproyect.library.util.MessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthenticationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
    private final OwnerOtp ownerOtp = new OwnerOtp();
    @Autowired
    private OwnerValidation ownerValidation;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private MessageUtils messageUtils;
    @Autowired
    private OwnerOtpRepository ownerOtpRepository;
    @Autowired
    private SendgridEmailSender sendgridEmailSender;
    @Autowired
    private PropertiesConfig propertiesConfig;
    @Autowired
    private InputValidation inputValidation;
    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    //-----------------Generate Token Login-----------------
    public AuthenticationResponse generateTokenLogin(OwnerLoginRequest ownerLoginRequest) {
        ownerValidation.checkOwnerLoginRequestFields(ownerLoginRequest);

        Owner ownerFound = ownerService.getOwnerByEmail(ownerLoginRequest.getUsername());

        if(!ownerFound.isActive()) throw new BadRequestException("Owner " + ownerFound.getEmail() +" is not active");

        isAuthenticatedOwner(ownerFound, ownerLoginRequest);

        final String token = jwtProvider.generate2FABearerToken(ownerFound.getEmail());
        final Map<String, String> params = new HashMap<>();
        params.put("sender", propertiesConfig.getSendgridSenderEmail());
        params.put("receiver", ownerFound.getEmail());

        EmailStructure emailStructure = EmailStructureUtils.buildEmailStructure(EmailStructureUtils.Type.TOKEN, params);

        messageUtils.buildOwnerOtp(ownerFound, ownerOtp, emailStructure);
        sendgridEmailSender.sendHtmlEmail(emailStructure);
        ownerOtpRepository.save(ownerOtp);

        return new AuthenticationResponse(BaseResponse.Status.SUCCESS, HttpStatus.CREATED.value(),
                "We send ur authentication code to ur email: " + ownerFound.getEmail(), token);
    }

    private void isAuthenticatedOwner(Owner owner, OwnerLoginRequest ownerLoginRequest){
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        owner.getEmail(),
                        ownerLoginRequest.getPassword()
                )
            );
            LOGGER.info("Owner {} authenticated", owner.getEmail());
        }catch(Exception e){
            throw new BadRequestException("Incorrect email or password");
        }
    }

    //-----------------Login-----------------
    public JwtResponse login(LoginRequest loginRequest) {
        ownerValidation.checkLoginRequestFields(loginRequest);

        LOGGER.info("Searching information for username: " + loginRequest.getUsername());

        Owner ownerFound = ownerService.getOwnerByEmail(loginRequest.getUsername());
        OwnerOtp lastOtpCodeRequest = searchLastOtpCodeRequest(ownerFound);

        ownerValidation.checkOptCode(loginRequest.getOtpCode(), lastOtpCodeRequest);

        return createPermission(ownerFound, loginRequest, lastOtpCodeRequest);
    }

    private OwnerOtp searchLastOtpCodeRequest(Owner owner){
        return ownerOtpRepository.findFirstByOwnerIdOrderByOtpCreationTimeDesc(owner.getId())
                .orElseThrow(() -> new BadRequestException("Otp code not found"));
    }

    private JwtResponse createPermission(Owner owner, LoginRequest loginRequest, OwnerOtp ownerOtp){
        Authentication authentication = null;
        try{
            ownerOtp.setWasUsed(true);
            ownerOtpRepository.save(ownerOtp);

            authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        owner.getEmail(),
                        loginRequest.getPassword()
                )
            );
        }catch (Exception e){
            LOGGER.info("Bad Credentials!!!");
            LOGGER.info("Error: " + e.getMessage());
            throw new BadRequestException("Bad Credentials!!!");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication, owner);
        UserDetails ownerDetails = (UserDetails) authentication.getPrincipal();

        return new JwtResponse(
                BaseResponse.Status.SUCCESS,
                HttpStatus.OK.value(),
                jwt,
                "Bearer",
                ownerDetails.getUsername(),
                ownerDetails.getAuthorities()
        );
    }

    //-----------------Reset Password Link-----------------
    public AuthenticationResponse resetPasswordLink(OwnerRequest ownerRequest){
        String defaultUtl = propertiesConfig.getApplicationBaseUrl();
        String redirectUrl = buildRedirectionUtl(defaultUtl);
        return createResetPasswordLink(ownerRequest.getUsername(), redirectUrl);
    }

    private AuthenticationResponse createResetPasswordLink(String email, String redirectUrl){
        inputValidation.checkEmail(email);

        Owner ownerFound = ownerService.getOwnerByEmail(email);

        String token = UUID.randomUUID().toString();

        final Map<String, String> params = new HashMap<>();
        params.put("sender", propertiesConfig.getSendgridSenderEmail());
        params.put("baseUrl", redirectUrl);
        params.put("token", token);
        params.put("addressee", ownerFound.getEmail());

        EmailStructure emailStructure = EmailStructureUtils.buildEmailStructure(EmailStructureUtils.Type.RESET, params);

        ownerService.createPasswordResetTokenForOwner(ownerFound, token);
        sendgridEmailSender.sendHtmlEmail(emailStructure);

        return new AuthenticationResponse(BaseResponse.Status.SUCCESS, HttpStatus.CREATED.value(),
                "We send a link to reset ur password to ur email: " + ownerFound.getEmail(), token);
    }

    private String buildRedirectionUtl(String defaultUrl){
        if(StringUtils.equals(defaultUrl, "localhost")){
            return "http://localhost:5173";
        }
        return "https://" + defaultUrl;
    }

    public BaseResponse resendOtpCode(OwnerRequest ownerRequest) {
        inputValidation.checkEmail(ownerRequest.getUsername());

        Owner ownerFound = ownerService.getOwnerByEmail(ownerRequest.getUsername());

        final Map<String, String> params = new HashMap<>();
        params.put("sender", propertiesConfig.getSendgridSenderEmail());
        params.put("receiver", ownerFound.getEmail());

        EmailStructure emailStructure = EmailStructureUtils.buildEmailStructure(EmailStructureUtils.Type.TOKEN, params);

        messageUtils.buildOwnerOtp(ownerFound, ownerOtp, emailStructure);
        sendgridEmailSender.sendHtmlEmail(emailStructure);
        ownerOtpRepository.save(ownerOtp);

        return new BaseResponse(BaseResponse.Status.SUCCESS, HttpStatus.CREATED.value(),
                "We re send ur authentication code to ur email: " + ownerFound.getEmail());
    }

    public BaseResponse updatePassword(ResetPasswordRequest resetPasswordRequest) {
        inputValidation.checkTokenData(resetPasswordRequest.getToken());
        inputValidation.checkUpdatePasswordData(resetPasswordRequest.getNewPassword());

        PasswordResetToken tokenFound = passwordResetTokenService.findByToken(resetPasswordRequest.getToken());

        inputValidation.checkPasswordResetToken(tokenFound);

        return messageUtils.resetOwnerPassword(passwordResetTokenService, passwordEncoder, resetPasswordRequest, tokenFound, ownerService);
    }
}
