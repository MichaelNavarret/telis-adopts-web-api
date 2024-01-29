package com.api.telisadoptproyect.api.configuration;

import com.api.telisadoptproyect.api.service.OwnerService;
import com.api.telisadoptproyect.api.service.RoleService;
import com.api.telisadoptproyect.library.entity.Owner;
import com.api.telisadoptproyect.library.entity.Role;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.OwnerRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private RoleService roleService;
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject ,Object permission) {
        validateInputs(authentication, permission);

        final List<String> requiredPermissions = (List<String>) permission;

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (userDetails == null) return false;

        Owner owner =ownerRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new BadRequestException("Owner not found with email: " + userDetails.getUsername())
        );

        if (BooleanUtils.isTrue(owner.isSuperAdmin())) return true;

        if(requiredPermissions.contains("super-admin")) return false;

        List<String> userRoleIds = authentication.getAuthorities().stream().map(Object::toString).toList();

        final List<Role> authorities = roleService.findAllByIdIn(userRoleIds);
        final List<String> permissions = roleService.getAllPermissionsFromRole(authorities);

        return permissions.stream().anyMatch(requiredPermissions::contains);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

    public void validateInputs(Authentication authentication, Object permission) {
        boolean isValidate = authentication != null && permission instanceof List && !Strings.isEmpty(permission.toString());
        if (!isValidate) throw new BadRequestException("Authentication and permission are required");
    }
}
