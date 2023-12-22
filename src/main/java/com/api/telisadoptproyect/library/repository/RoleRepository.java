package com.api.telisadoptproyect.library.repository;

import com.api.telisadoptproyect.library.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String>{
    List<Role> findAllByIdIn(List<String> ids);
}
