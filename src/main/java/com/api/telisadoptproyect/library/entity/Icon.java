package com.api.telisadoptproyect.library.entity;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Icon {
    @Id
    private String id;
    private String code;
    private String iconUrl;
    private boolean exclusive;
    private boolean active;
    @ManyToMany
    @JoinTable(name = "rel_icon_owner",
               joinColumns = @JoinColumn(name = "iconId"),
               inverseJoinColumns = @JoinColumn(name = "ownerId"))
    private List<Owner> availableFor;
    private Date createdOn;

    public Icon() {
        this.id = UUID.randomUUID().toString();
        this.active = false;
        this.availableFor = Collections.emptyList();
        this.createdOn = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    public List<Owner> getAvailableFor() {
        return availableFor;
    }

    public void setAvailableFor(List<Owner> availableFor) {
        this.availableFor = availableFor;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
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
}
