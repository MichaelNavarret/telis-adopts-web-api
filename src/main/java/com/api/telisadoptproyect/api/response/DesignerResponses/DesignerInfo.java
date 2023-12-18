package com.api.telisadoptproyect.api.response.DesignerResponses;

import com.api.telisadoptproyect.library.entity.Designer;

public class DesignerInfo {
    private String id;
    private String name;

    public DesignerInfo(Designer designer) {
        this.id = designer.getId();
        this.name = designer.getName();
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
