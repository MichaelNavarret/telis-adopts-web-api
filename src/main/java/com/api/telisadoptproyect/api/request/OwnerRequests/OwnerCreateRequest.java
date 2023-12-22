package com.api.telisadoptproyect.api.request.OwnerRequests;

public class OwnerCreateRequest {
    private String nickName;
    private String email;

    public OwnerCreateRequest() {
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
}
