package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.configuration.PropertiesConfig;
import com.api.telisadoptproyect.api.configuration.SendgridEmailSender;
import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerLoginRequest;
import com.api.telisadoptproyect.api.response.AuthenticationResponses.AuthenticationResponse;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.security.JwtProvider;
import com.api.telisadoptproyect.api.validation.OwnerValidation;
import com.api.telisadoptproyect.library.entity.Owner;
import com.api.telisadoptproyect.library.entity.OwnerOtp;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.OwnerOtpRepository;
import com.api.telisadoptproyect.library.util.EmailStructure;
import com.api.telisadoptproyect.library.util.EmailStructureUtils;
import com.api.telisadoptproyect.library.util.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
    private final OwnerOtp ownerOtp = new OwnerOtp();
    @Autowired
    private OwnerValidation ownerValidation;
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
    public AuthenticationResponse generateTokenLogin(OwnerLoginRequest ownerLoginRequest) {
        ownerValidation.checkOwnerLoginRequestFields(ownerLoginRequest);

        Owner ownerFound = ownerService.getOwnerByEmail(ownerLoginRequest.getEmail());

        if(!ownerFound.isActive()) throw new BadRequestException("Owner " + ownerFound.getEmail() +" is not active");

        //isAuthenticatedOwner(ownerFound, ownerLoginRequest);

        final String token = jwtProvider.generate2FABearerToken(ownerFound.getEmail());
        final Map<String, String> params = new HashMap<>();
        params.put("sender", propertiesConfig.getSendgridSenderEmail());
        params.put("receiver", ownerFound.getEmail());

        EmailStructure emailStructure = EmailStructureUtils.buildEmailStructure(EmailStructureUtils.Type.TOKEN, params);

        messageUtils.buildOwnerOtp(ownerFound, ownerOtp, emailStructure);
        sendgridEmailSender.sendHtmlEmail(emailStructure);
        ownerOtpRepository.save(ownerOtp);

        return new AuthenticationResponse(BaseResponse.Status.SUCCESS, HttpStatus.CREATED.value(),
                "We send ur authentication code to ur email", token);
    }

    // Private Methods
    private void isAuthenticatedOwner(Owner owner, OwnerLoginRequest ownerLoginRequest){
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        owner.getNickName(),
                        ownerLoginRequest.getPassword()
                )
            );
        }catch(Exception e){
            throw new BadRequestException("Incorrect email or password");
        }
    }
}
