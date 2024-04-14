package com.api.telisadoptproyect.api.request.BadgeRequests;

public class CreateBadgeRequest {
    private String name;
    private String code;
    private String description;

    public CreateBadgeRequest(){}

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
}
