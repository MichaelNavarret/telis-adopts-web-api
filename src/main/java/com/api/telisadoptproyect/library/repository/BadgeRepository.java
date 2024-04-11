package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BadgeRepository extends JpaRepository<Badge, String>, QuerydslPredicateExecutor<Badge> {

}
