package com.api.telisadoptproyect.api.response.BadgeResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Badge;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BadgeCollectionResponse extends BaseResponse {
    private List<BadgeInfo> badgeInfoList;

    public BadgeCollectionResponse() {
        super(Status.SUCCESS, 200);
        this.badgeInfoList = Collections.emptyList();
    }
    public BadgeCollectionResponse(List<Badge> badges) {
        this();
        this.badgeInfoList = badges.stream().map(BadgeInfo::new).collect(Collectors.toList());
    }

    public BadgeCollectionResponse(Page<Badge> badges){
        this();
        this.badgeInfoList = badges.stream().map(BadgeInfo::new).collect(Collectors.toList());
    }

    public List<BadgeInfo> getBadgeInfoList() {
        return badgeInfoList;
    }

    public void setBadgeInfoList(List<BadgeInfo> badgeInfoList) {
        this.badgeInfoList = badgeInfoList;
    }
}
