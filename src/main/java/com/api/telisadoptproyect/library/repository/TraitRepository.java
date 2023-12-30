package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.Trait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface TraitRepository extends JpaRepository<Trait, String>, QuerydslPredicateExecutor<Trait> {
    Optional<Trait> findByIdAndSpecie_Id(String traitId, String specieId);
}
