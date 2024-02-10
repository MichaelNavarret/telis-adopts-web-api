package com.api.telisadoptproyect.library.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Owner {
    @Id
    private String id;
    private String nickName;
    private String email;
    private String password;
    private boolean active;
    private Date createdOn;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rel_owner_role", joinColumns = @JoinColumn(name = "ownerId"),
                inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "owner")
    private List<OwnerOtp> otpList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rel_owner_favorite_adopt", joinColumns = @JoinColumn(name = "ownerId"),
            inverseJoinColumns = @JoinColumn(name = "adoptId"))
    private Set<Adopt> favorites;
    private boolean superAdmin;
    @ManyToOne
    @JoinColumn(name = "profileIconId")
    private Icon profileIcon;
    private String discord;
    private String instagram;
    private String twitter;
    private String toyhouse;
    private String devianart;
    public Owner() {
        this.id = UUID.randomUUID().toString();
        this.active = true;
        this.createdOn = new Date();
        this.superAdmin = false;
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

    public List<OwnerOtp> getOtpList() {
        return otpList;
    }

    public void setOtpList(List<OwnerOtp> otpList) {
        this.otpList = otpList;
    }

    public boolean isSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    public Set<Adopt> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Adopt> favorites) {
        this.favorites = favorites;
    }

    public Icon getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(Icon profileIcon) {
        this.profileIcon = profileIcon;
    }

    public String getDiscord() {
        return discord;
    }

    public void setDiscord(String discord) {
        this.discord = discord;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getToyhouse() {
        return toyhouse;
    }

    public void setToyhouse(String toyhouse) {
        this.toyhouse = toyhouse;
    }

    public String getDevianart() {
        return devianart;
    }

    public void setDevianart(String devianart) {
        this.devianart = devianart;
    }
}
