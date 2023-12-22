package com.api.telisadoptproyect.library.util;

import com.api.telisadoptproyect.api.request.ResetPasswordRequest;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.service.OwnerService;
import com.api.telisadoptproyect.api.service.PasswordResetTokenService;
import com.api.telisadoptproyect.library.entity.Owner;
import com.api.telisadoptproyect.library.entity.OwnerOtp;
import com.api.telisadoptproyect.library.entity.PasswordResetToken;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class MessageUtils {
    @Autowired
    private OwnerService ownerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtils.class);

    public BaseResponse resetOwnerPassword(PasswordResetTokenService passwordResetTokenService, PasswordEncoder passwordEncoder,
                                           ResetPasswordRequest resetPasswordRequest, PasswordResetToken tokenFound,
                                           OwnerService ownerService){

        final Owner ownerFound = ownerService.getOwnerById(tokenFound.getOwner().getId());
        try{
            ownerFound.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
            tokenFound.setWasUsed(true);
            passwordResetTokenService.save(tokenFound);
            ownerService.save(ownerFound);
            LOGGER.info("Password reset successfully");

            return new BaseResponse(
                    BaseResponse.Status.SUCCESS,
                    HttpStatus.CREATED.value(),
                    "Password reset successfully"
            );
        }catch(Exception e){
            throw new BadRequestException("Error trying to reset password / " + e.getMessage());
        }
    }

    public void buildOwnerOtp(Owner owner, OwnerOtp ownerOtp, EmailStructure emailStructure){
        if (owner == null) throw new BadRequestException("Owner null");
        if (emailStructure == null ) throw new BadRequestException("EmailStructure null");

        LocalDateTime otpCreationTime = LocalDateTime.now();
        LocalDateTime otpExpirationTime = otpCreationTime.plusMinutes(5);

        ownerOtp.setId(UUID.randomUUID().toString());
        ownerOtp.setOtpCode(String.valueOf(emailStructure.getBodyParam()));
        ownerOtp.setOtpCreationTime(otpCreationTime);
        ownerOtp.setOtpExpirationTime(otpExpirationTime);
        ownerOtp.setWasUsed(false);
        ownerOtp.setOwner(owner);
    }
}
