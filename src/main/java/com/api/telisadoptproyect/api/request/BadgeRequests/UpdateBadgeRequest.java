package com.api.telisadoptproyect.api.request.BadgeRequests;

public class UpdateBadgeRequest {
    private String name;
    private String code;
    private String description;
    private Boolean active;

    public UpdateBadgeRequest(){}

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
