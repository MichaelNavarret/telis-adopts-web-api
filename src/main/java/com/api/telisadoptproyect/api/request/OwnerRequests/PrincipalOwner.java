package com.api.telisadoptproyect.api.request.OwnerRequests;

import com.api.telisadoptproyect.library.entity.Owner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PrincipalOwner implements UserDetails {
    private String nickName;
    private  String email;
    private String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public PrincipalOwner(String nickName, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static PrincipalOwner build(Owner owner){
        List<GrantedAuthority> authorities =
                owner.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getId())).collect(Collectors.toList());
        return new PrincipalOwner(owner.getNickName(), owner.getEmail(), owner.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public void setPassword(String password) {
        this.password = password;
    }
}
