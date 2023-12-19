package com.api.telisadoptproyect.api.response.OwnerResponses;

import com.api.telisadoptproyect.library.entity.Owner;

public class OwnerInfo {
    private String id;
    private String name;

    public OwnerInfo(Owner owner){
        this.id = owner.getId();
        this.name = owner.getName();
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
}
