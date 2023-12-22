package com.api.telisadoptproyect.api.request.OwnerRequests;

public class OwnerLoginRequest extends OwnerRequest {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
