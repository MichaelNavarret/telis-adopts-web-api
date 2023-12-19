package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.Specie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SpecieRepository extends JpaRepository<Specie, String >, QuerydslPredicateExecutor<Specie> {

}
