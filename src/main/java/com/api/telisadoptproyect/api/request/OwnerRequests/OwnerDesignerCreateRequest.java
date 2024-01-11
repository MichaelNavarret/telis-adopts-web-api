package com.api.telisadoptproyect.api.request.OwnerRequests;

public class OwnerDesignerCreateRequest {
    private String id;
    private boolean notRegisteredDesigner;

    public OwnerDesignerCreateRequest() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isNotRegisteredDesigner() {
        return notRegisteredDesigner;
    }

    public void setNotRegisteredDesigner(boolean notRegisteredDesigner) {
        this.notRegisteredDesigner = notRegisteredDesigner;
    }
}
