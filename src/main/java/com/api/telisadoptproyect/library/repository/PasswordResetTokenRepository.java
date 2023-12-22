package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    List<PasswordResetToken> findAllByOwnerId(String ownerId);
}
