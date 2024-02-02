package com.api.telisadoptproyect.api.response.OwnerResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Owner;

import java.util.Collections;
import java.util.List;

public class OwnerSingletonResponse extends BaseResponse {
    private OwnerSingletonInfo ownerSingletonInfo;
    private List<String> badgesCode;

    public OwnerSingletonResponse(Status status, Integer code, Owner owner){
        super(status, code);
        this.ownerSingletonInfo = new OwnerSingletonInfo(owner);
        this.badgesCode = Collections.emptyList();
    }
    public OwnerSingletonInfo getOwnerSingletonInfo() {
        return ownerSingletonInfo;
    }

    public void setOwnerSingletonInfo(OwnerSingletonInfo ownerSingletonInfo) {
        this.ownerSingletonInfo = ownerSingletonInfo;
    }
    public List<String> getBadgesCode() {
        return badgesCode;
    }

    public void setBadgesCode(List<String> badgesCode) {
        this.badgesCode = badgesCode;
    }
}
