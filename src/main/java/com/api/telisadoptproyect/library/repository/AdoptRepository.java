package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.Adopt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AdoptRepository extends JpaRepository<Adopt, String>, QuerydslPredicateExecutor<Adopt> {
    Adopt findFirstByCreationTypeOrderByCreatedOnDesc(Adopt.CreationType type);
}
