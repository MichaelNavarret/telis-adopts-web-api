package com.api.telisadoptproyect.api.response.BadgeResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Badge;

public class BadgeSingletonResponse extends BaseResponse {
    private BadgeInfo badgeInfo;

    public BadgeSingletonResponse (Status status, Integer code, Badge badge){
        super(status, code);
        this.badgeInfo = new BadgeInfo(badge);
    }

    public BadgeInfo getBadgeInfo() {
        return badgeInfo;
    }

    public void setBadgeInfo(BadgeInfo badgeInfo) {
        this.badgeInfo = badgeInfo;
    }
}
