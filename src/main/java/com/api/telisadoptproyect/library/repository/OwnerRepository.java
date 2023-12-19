package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, String> {
}
