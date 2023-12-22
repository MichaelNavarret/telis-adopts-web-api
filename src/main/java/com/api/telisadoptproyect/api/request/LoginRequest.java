package com.api.telisadoptproyect.api.request;

import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerLoginRequest;

public class LoginRequest extends OwnerLoginRequest {
    private String otpCode;
    public LoginRequest() {
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }
}
