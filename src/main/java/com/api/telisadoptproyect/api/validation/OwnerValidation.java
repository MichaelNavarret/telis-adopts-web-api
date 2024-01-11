package com.api.telisadoptproyect.api.validation;

import com.api.telisadoptproyect.api.request.LoginRequest;
import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerLoginRequest;
import com.api.telisadoptproyect.library.entity.Owner;
import com.api.telisadoptproyect.library.entity.OwnerOtp;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.OwnerRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class OwnerValidation {
    @Autowired
    private OwnerRepository ownerRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(OwnerValidation.class);

    public void checkOwnerLoginRequestFields(OwnerLoginRequest ownerLoginRequest){
        LOGGER.info("Checking owner information....");
        if(StringUtils.isBlank(ownerLoginRequest.getUsername())){
            throw new BadRequestException("Email is required");
        }
        if(StringUtils.isBlank(ownerLoginRequest.getPassword())){
            throw new BadRequestException("Password is required");
        }
    }

    public void checkLoginRequestFields(LoginRequest loginRequest){
        LOGGER.info("Checking owner information....");
        if(StringUtils.isBlank(loginRequest.getUsername())){
            throw new BadRequestException("Email is required");
        }
        if(StringUtils.isBlank(loginRequest.getPassword())){
            throw new BadRequestException("Password is required");
        }
        if(StringUtils.isBlank(loginRequest.getOtpCode())){
            throw new BadRequestException("Otp is required");
        }
    }

    public void checkOptCode(String otpCode, OwnerOtp lastOwnerOtp){
        if (lastOwnerOtp.isWasUsed()){
            throw new BadRequestException("Otp code was used");
        }

        if(LocalDateTime.now().isAfter(lastOwnerOtp.getOtpExpirationTime())){
            throw new BadRequestException("Otp code is expired");
        }

        if(!otpCode.equalsIgnoreCase(lastOwnerOtp.getOtpCode())){
            throw new BadRequestException("Otp code is not valid");
        }
    }

    public void checkIfNicknameOwnerExist (String nickname){
        Optional<Owner> foundOwner = ownerRepository.findByNickName(nickname);
        if (foundOwner.isPresent()) throw new BadRequestException("Nickname already exist");
    }

    public void checkIfEmailOwnerExist (String email){
        Optional<Owner> foundOwner = ownerRepository.findByEmail(email);
        if (foundOwner.isPresent()) throw new BadRequestException("Email already exist");
    }
}
