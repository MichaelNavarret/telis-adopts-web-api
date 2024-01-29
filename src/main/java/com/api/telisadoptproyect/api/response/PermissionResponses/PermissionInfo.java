package com.api.telisadoptproyect.api.response.PermissionResponses;

import com.api.telisadoptproyect.library.entity.Permission;

public class PermissionInfo {
    private String id;
    private String name;
    private String code;
    private boolean active;

    public PermissionInfo() {
    }

    public PermissionInfo(Permission permission) {
        this.id = permission.getId();
        this.name = permission.getName();
        this.code = permission.getCode();
        this.active = permission.isActive();
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}


