package com.api.telisadoptproyect.api.response.BadgeResponses;

import com.api.telisadoptproyect.library.entity.Badge;

import java.util.Date;

public class BadgeInfo {
    private String id;
    private String name;
    private String code;
    private String description;
    private String badgeUrl;
    private Date createdOn;
    private Boolean active;

    public BadgeInfo(Badge badge) {
        this.id = badge.getId();
        this.name = badge.getName();
        this.code = badge.getCode();
        this.description = badge.getDescription();
        this.badgeUrl = badge.getBadgeUrl();
        this.createdOn = badge.getCreatedOn();
        this.active = badge.isActive();
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
