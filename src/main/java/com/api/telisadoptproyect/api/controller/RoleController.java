package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.request.RoleRequests.RoleCreateRequest;
import com.api.telisadoptproyect.api.response.RoleResponses.RoleCollectionResponse;
import com.api.telisadoptproyect.api.response.RoleResponses.RoleSingletonResponse;
import com.api.telisadoptproyect.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("")
    @PreAuthorize("hasPermission(#null, {'can-write-roles'})")
    public ResponseEntity<RoleSingletonResponse> createRole(
            @RequestBody RoleCreateRequest roleCreateRequest) {
        return ResponseEntity.ok(roleService.createRole(roleCreateRequest));
    }

    @GetMapping("")
    @PreAuthorize("hasPermission(#null, {'can-read-roles', 'can-write-roles'})")
    public ResponseEntity<RoleCollectionResponse> getRolesCollection() {
        return ResponseEntity.ok(roleService.getRolesCollection());
    }

    @GetMapping("/{roleId}/permissions")
    @PreAuthorize("hasPermission(#null, {'can-read-roles', 'can-write-roles'})")
    public ResponseEntity<RoleSingletonResponse> getRoleById(
            @PathVariable("roleId") String roleId) {
        return ResponseEntity.ok(roleService.getPermissionByRole(roleId));
    }
}
