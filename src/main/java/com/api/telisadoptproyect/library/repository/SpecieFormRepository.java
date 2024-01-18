package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.SpecieForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecieFormRepository extends JpaRepository<SpecieForm, String> {
}
