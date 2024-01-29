package com.api.telisadoptproyect.api.request.RoleRequests;

public class RoleCreateRequest {
    private String name;
    private String description;

    public RoleCreateRequest() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
