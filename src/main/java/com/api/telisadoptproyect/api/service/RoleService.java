package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.request.RoleRequests.RoleCreateRequest;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.api.response.RoleResponses.RoleCollectionResponse;
import com.api.telisadoptproyect.api.response.RoleResponses.RoleSingletonResponse;
import com.api.telisadoptproyect.library.entity.Role;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.repository.RoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public RoleSingletonResponse createRole(RoleCreateRequest roleCreateRequest) {
        if (roleCreateRequest == null) throw new BadRequestException("RoleCreateRequest is null");
        if (StringUtils.isBlank(roleCreateRequest.getName())) throw new BadRequestException("RoleCreateRequest.name is null");
        if (StringUtils.isBlank(roleCreateRequest.getDescription())) throw new BadRequestException("RoleCreateRequest.description is null");

        Role role = new Role();
        role.setName(roleCreateRequest.getName());
        role.setDescription(roleCreateRequest.getDescription());

        return new RoleSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.OK.value(), roleRepository.save(role));
    }
    public RoleCollectionResponse getRolesCollection() {
        return new RoleCollectionResponse(roleRepository.findAll());
    }
    public RoleSingletonResponse getPermissionByRole(String roleId) {
        if(StringUtils.isBlank(roleId)) throw new BadRequestException("roleId is null");
        Role role = roleRepository.findById(roleId).orElse(null);
        if(role == null) throw new BadRequestException("Role not found");
        return new RoleSingletonResponse(BaseResponse.Status.SUCCESS, HttpStatus.OK.value(), role);
    }



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
