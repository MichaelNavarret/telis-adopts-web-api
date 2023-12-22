package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.library.entity.PasswordResetToken;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.PasswordResetTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PasswordResetTokenService {
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetToken findByToken(String token){
        return passwordResetTokenRepository.findByToken(token).orElseThrow(
                () -> new BadRequestException("Error: Token not found")
        );
    }

    public void save(PasswordResetToken passwordResetToken){
        passwordResetTokenRepository.save(passwordResetToken);
    }
}
