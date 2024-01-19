package com.api.telisadoptproyect.api.response.OwnerResponses;

import com.api.telisadoptproyect.library.entity.Owner;

public class OwnerInfo {
    private String id;
    private String nickName;
    private String email;
    private boolean status;

    public OwnerInfo(Owner owner){
        this.id = owner.getId();
        this.nickName = owner.getNickName();
        this.email = owner.getEmail();
        this.status = owner.isActive();
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
}
