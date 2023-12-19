package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.Adopt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptRepository extends JpaRepository<Adopt, String> {
}
