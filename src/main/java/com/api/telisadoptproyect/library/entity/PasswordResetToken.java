package com.api.telisadoptproyect.library.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PasswordResetToken {
    @Id
    private String id;
    @Column(nullable = false)
    private String token;
    @OneToOne(targetEntity = Owner.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "owner_id")
    private Owner owner;
    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime expiryDate;
    @Column(nullable = false)
    private Boolean wasUsed;

    public PasswordResetToken(String token) {
        this.token = token;
        this.wasUsed = false;
        this.expiryDate = LocalDateTime.now().plusMinutes(5);
    }

    public PasswordResetToken(Owner owner, String token) {
        this.owner = owner;
        this.token = token;
        this.wasUsed = false;
        this.expiryDate = LocalDateTime.now().plusMinutes(5);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getWasUsed() {
        return wasUsed;
    }

    public void setWasUsed(Boolean wasUsed) {
        this.wasUsed = wasUsed;
    }
}
