package com.api.telisadoptproyect.api.response.AuthenticationResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationResponse extends BaseResponse {
    private String token;
    private String bearer = "Bearer";
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean canSkip2fa;

    public AuthenticationResponse(Status status, Integer code, String message, String token){
        super(status, code, message);
        this.token = token;
    }

    public AuthenticationResponse(Status status, Integer code, String token, boolean canSkip2fa,
                                  String bearer, String email,
                                  Collection<? extends GrantedAuthority> authorities){
        super(status, code);
        this.token = token;
        this.bearer = bearer;
        this.email = email;
        this.canSkip2fa = canSkip2fa;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public boolean isCanSkip2fa() {
        return canSkip2fa;
    }

    public void setCanSkip2fa(boolean canSkip2fa) {
        this.canSkip2fa = canSkip2fa;
    }
}
