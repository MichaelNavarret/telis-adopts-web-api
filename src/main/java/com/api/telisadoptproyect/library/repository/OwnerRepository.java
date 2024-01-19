package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, String>, QuerydslPredicateExecutor<Owner> {
    Optional<Owner> findByEmail(String email);
    Optional<Owner> findByNickName(String nickName);
}
