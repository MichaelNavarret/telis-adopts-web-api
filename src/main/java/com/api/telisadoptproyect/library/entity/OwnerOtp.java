package com.api.telisadoptproyect.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class OwnerOtp {
    @Id
    private String id;
    @Column(nullable = false)
    private String otpCode;
    @Column(nullable = false)
    private LocalDateTime otpCreationTime;
    @Column(nullable = false)
    private LocalDateTime otpExpirationTime;
    @Column(nullable = false)
    private boolean wasUsed;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnore
    private Owner owner;

    public OwnerOtp() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public LocalDateTime getOtpCreationTime() {
        return otpCreationTime;
    }

    public void setOtpCreationTime(LocalDateTime otpCreationTime) {
        this.otpCreationTime = otpCreationTime;
    }

    public LocalDateTime getOtpExpirationTime() {
        return otpExpirationTime;
    }

    public void setOtpExpirationTime(LocalDateTime otpExpirationTime) {
        this.otpExpirationTime = otpExpirationTime;
    }

    public boolean isWasUsed() {
        return wasUsed;
    }

    public void setWasUsed(boolean wasUsed) {
        this.wasUsed = wasUsed;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
