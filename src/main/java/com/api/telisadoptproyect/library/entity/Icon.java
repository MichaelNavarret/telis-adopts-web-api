package com.api.telisadoptproyect.library.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Icon {
    @Id
    private String id;
    private String code;
    private String iconUrl;
    @ManyToMany
    @JoinTable(name = "rel_icon_owner",
               joinColumns = @JoinColumn(name = "iconId"),
               inverseJoinColumns = @JoinColumn(name = "ownerId"))
    private List<Owner> availableFor;

    public Icon() {
        this.id = UUID.randomUUID().toString();
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
}
