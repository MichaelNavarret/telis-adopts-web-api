package com.api.telisadoptproyect.api.response.RoleResponses;

import com.api.telisadoptproyect.api.response.PermissionResponses.PermissionInfo;
import com.api.telisadoptproyect.library.entity.Owner;
import com.api.telisadoptproyect.library.entity.Role;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RoleInfo {
    private String id;
    private String name;
    private boolean active;
    private List<PermissionInfo> permissions;
    private String description;
    private Date createdOn;
    private Date updatedOn;
    private long nOwners;

    public RoleInfo(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.active = role.isActive();
        this.permissions = role.getPermissions() != null? role.getPermissions().stream().map(PermissionInfo::new).collect(Collectors.toList()) : null;
        this.description = role.getDescription();
        this.createdOn = role.getCreatedOn();
        this.updatedOn = role.getUpdatedOn();
        this.nOwners = role.getOwners() != null? role.getOwners().stream().filter(Owner::isActive).count() : 0;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<PermissionInfo> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionInfo> permissions) {
        this.permissions = permissions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public long getNOwners() {
        return nOwners;
    }

    public void setNOwners(long nOwners) {
        this.nOwners = nOwners;
    }
}
