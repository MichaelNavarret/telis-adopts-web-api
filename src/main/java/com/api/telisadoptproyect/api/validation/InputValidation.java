package com.api.telisadoptproyect.api.validation;

import com.api.telisadoptproyect.commons.Constants;
import com.api.telisadoptproyect.library.entity.PasswordResetToken;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Component
public class InputValidation {
    public void checkEmail(String email) {
        if (StringUtils.isBlank(email)) {
            throw new BadRequestException("Email is required");
        }

        if(!Pattern.compile(Constants.EMAIL_REGEX).matcher(email).matches()) {
            throw new BadRequestException("Email Format is not valid");
        }
    }

    public void checkTokenData(String token){
        if(StringUtils.isBlank(token)) {
            throw new BadRequestException("Token is required");
        }
    }

    public void checkUpdatePasswordData(String newPassword){
        if(StringUtils.isBlank(newPassword)) {
            throw new BadRequestException("New password is required");
        }

        if(!Pattern.compile(Constants.PASS_REGEX).matcher(newPassword).matches()) {
            throw new BadRequestException("New password format is not valid");
        }
    }

    public void checkPasswordResetToken(PasswordResetToken tokenFound){
        if(ObjectUtils.isEmpty(tokenFound)) {
            throw new BadRequestException("Token is required");
        }

        if(tokenFound.getWasUsed()){
            throw new BadRequestException("Reset password token was used");
        }

        if(LocalDateTime.now().isAfter(tokenFound.getExpiryDate())){
            throw new BadRequestException("Reset password token is expired");
        }
    }
}
