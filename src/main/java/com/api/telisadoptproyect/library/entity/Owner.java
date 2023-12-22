package com.api.telisadoptproyect.library.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Owner {
    @Id
    private String id;
    private String name;
    private String nickName;
    private String email;
    private String password;
    private boolean active;
    private Date createdOn;

    @ManyToMany
    @JoinTable(name = "rel_owner_role", joinColumns = @JoinColumn(name = "ownerId"),
                inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "owner")
    private List<OwnerOtp> otpList;

    public Owner() {
        this.id = UUID.randomUUID().toString();
        this.active = true;
        this.createdOn = new Date();
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
