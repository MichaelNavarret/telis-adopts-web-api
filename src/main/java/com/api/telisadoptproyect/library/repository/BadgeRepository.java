package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface BadgeRepository extends JpaRepository<Badge, String>, QuerydslPredicateExecutor<Badge> {
  Optional<Badge> findByName(String name);
  Optional<Badge> findByCode(String code);

}
