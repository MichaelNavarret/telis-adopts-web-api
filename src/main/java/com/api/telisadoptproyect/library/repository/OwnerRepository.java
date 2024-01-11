package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, String> {
    Optional<Owner> findByEmail(String email);
    Optional<Owner> findByNickName(String nickName);
}
