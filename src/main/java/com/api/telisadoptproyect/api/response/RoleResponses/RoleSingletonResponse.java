package com.api.telisadoptproyect.api.response.RoleResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Role;

public class RoleSingletonResponse extends BaseResponse {
    private RoleSingletonInfo roleSingletonInfo;

    public RoleSingletonResponse(Status status, Integer code, Role role) {
        super(status, code);
        this.roleSingletonInfo = new RoleSingletonInfo(role);
    }

    public RoleSingletonInfo getRoleSingletonInfo() {
        return roleSingletonInfo;
    }

    public void setRoleSingletonInfo(RoleSingletonInfo roleSingletonInfo) {
        this.roleSingletonInfo = roleSingletonInfo;
    }
}
