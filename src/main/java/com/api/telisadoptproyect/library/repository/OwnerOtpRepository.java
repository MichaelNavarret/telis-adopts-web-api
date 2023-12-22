package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.OwnerOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerOtpRepository extends JpaRepository<OwnerOtp, String> {
    Optional<OwnerOtp> findFirstByOwnerOwnerIdOrderByOtpCreationTimeDesc(String ownerId);
}
