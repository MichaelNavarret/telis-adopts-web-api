package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.Trait;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraitRepository extends JpaRepository<Trait, String> {
}
