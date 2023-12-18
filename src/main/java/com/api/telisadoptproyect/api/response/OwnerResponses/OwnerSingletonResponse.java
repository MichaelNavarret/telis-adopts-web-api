package com.api.telisadoptproyect.api.response.OwnerResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Owner;

public class OwnerSingletonResponse extends BaseResponse {
    private OwnerSingletonInfo ownerSingletonInfo;

    public OwnerSingletonResponse(Status status, Integer code, Owner owner){
        super(status, code);
        this.ownerSingletonInfo = new OwnerSingletonInfo(owner);
    }

    public OwnerSingletonInfo getOwnerSingletonInfo() {
        return ownerSingletonInfo;
    }

    public void setOwnerSingletonInfo(OwnerSingletonInfo ownerSingletonInfo) {
        this.ownerSingletonInfo = ownerSingletonInfo;
    }
}
