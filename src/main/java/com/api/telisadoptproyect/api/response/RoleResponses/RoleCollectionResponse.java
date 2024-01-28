package com.api.telisadoptproyect.api.response.RoleResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Role;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RoleCollectionResponse extends BaseResponse {
    private List<RoleInfo> roleInfoList;

    public RoleCollectionResponse(){
        super(Status.SUCCESS, 200);
        this.roleInfoList= Collections.emptyList();
    }

    public RoleCollectionResponse(List<Role> roles) {
        this();
        this.roleInfoList = roles.stream().map(RoleInfo::new).collect(Collectors.toList());
    }

    public List<RoleInfo> getRoleInfoList() {
        return roleInfoList;
    }

    public void setRoleInfoList(List<RoleInfo> roleInfoList) {
        this.roleInfoList = roleInfoList;
    }
}
