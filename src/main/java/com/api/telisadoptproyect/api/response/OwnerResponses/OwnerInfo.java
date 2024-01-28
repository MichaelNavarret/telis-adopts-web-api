package com.api.telisadoptproyect.api.response.OwnerResponses;

import com.api.telisadoptproyect.api.response.RoleResponses.OwnerRoleSummaryInfo;
import com.api.telisadoptproyect.library.entity.Owner;
import com.api.telisadoptproyect.library.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

public class OwnerInfo {
    private String id;
    private String nickName;
    private String email;
    private boolean status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OwnerRoleSummaryInfo role;

    public OwnerInfo(Owner owner){
        this.id = owner.getId();
        this.nickName = owner.getNickName();
        this.email = owner.getEmail();
        this.status = owner.isActive();
        Role role = !owner.getRoles().isEmpty() ? owner.getRoles().iterator().next() : null;
        if(role != null){
            this.role = new OwnerRoleSummaryInfo(role);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public OwnerRoleSummaryInfo getRole() {
        return role;
    }

    public void setRole(OwnerRoleSummaryInfo role) {
        this.role = role;
    }
}
