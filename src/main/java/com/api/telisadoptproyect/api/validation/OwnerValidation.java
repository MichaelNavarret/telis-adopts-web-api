package com.api.telisadoptproyect.api.validation;

import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerLoginRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OwnerValidation {
    private static final Logger LOGGER = LoggerFactory.getLogger(OwnerValidation.class);

    public void checkOwnerLoginRequestFields(OwnerLoginRequest ownerLoginRequest){
        LOGGER.info("Checking owner information....");
        if(StringUtils.isBlank(ownerLoginRequest.getEmail())){
            throw new RuntimeException("Email is required");
        }
        if(StringUtils.isBlank(ownerLoginRequest.getPassword())){
            throw new RuntimeException("Password is required");
        }
    }
}
