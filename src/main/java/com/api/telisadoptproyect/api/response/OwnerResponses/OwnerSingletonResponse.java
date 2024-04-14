package com.api.telisadoptproyect.api.response.OwnerResponses;

import com.api.telisadoptproyect.api.response.BadgeResponses.BadgeInfo;
import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Owner;

import java.util.Collections;
import java.util.List;

public class OwnerSingletonResponse extends BaseResponse {
    private OwnerSingletonInfo ownerSingletonInfo;
    private List<BadgeInfo> badges;

    public OwnerSingletonResponse(Status status, Integer code, Owner owner){
        super(status, code);
        this.ownerSingletonInfo = new OwnerSingletonInfo(owner);
        this.badges = Collections.emptyList();
    }
    public OwnerSingletonInfo getOwnerSingletonInfo() {
        return ownerSingletonInfo;
    }

    public void setOwnerSingletonInfo(OwnerSingletonInfo ownerSingletonInfo) {
        this.ownerSingletonInfo = ownerSingletonInfo;
    }

    public List<BadgeInfo> getBadges() {
        return badges;
    }

    public void setBadges(List<BadgeInfo> badges) {
        this.badges = badges;
    }
}
