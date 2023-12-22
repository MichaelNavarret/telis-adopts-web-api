package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.library.entity.Role;
import com.api.telisadoptproyect.library.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAllByIdIn(List<String> ids) {
        return roleRepository.findAllByIdIn(ids);
    }

    public List<String> getAllPermissionsFromRole(final List<Role> authorities) {
        final List<String> permissions = new ArrayList<>();
        authorities.forEach(role -> {
            role.getPermissions().forEach(permission -> {
                permissions.add(permission.getName());
            });
        });
        return permissions;
    }
}
