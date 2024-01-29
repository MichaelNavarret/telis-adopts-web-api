package com.api.telisadoptproyect.api.response.RoleResponses;

import com.api.telisadoptproyect.library.entity.Role;

public class OwnerRoleSummaryInfo {
    private String id;
    private String name;

    public OwnerRoleSummaryInfo(Role role){
        this.id = role.getId();
        this.name = role.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
