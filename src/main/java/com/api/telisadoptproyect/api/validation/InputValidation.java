package com.api.telisadoptproyect.api.validation;

import com.api.telisadoptproyect.commons.Constants;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

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
}
