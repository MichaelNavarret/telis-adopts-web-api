package com.api.telisadoptproyect.api.response.IconResponses;

import com.api.telisadoptproyect.api.response.OwnerResponses.OwnerInfo;
import com.api.telisadoptproyect.library.entity.Icon;

import java.util.List;

public class IconInfo {
    private String id;
    private String code;
    private String iconUrl;
    private List<OwnerInfo> availableFor;

    public IconInfo(Icon icon) {
        this.id = icon.getId();
        this.code = icon.getCode();
        this.iconUrl = icon.getIconUrl();
        this.availableFor = icon.getAvailableFor().stream().map(OwnerInfo::new).toList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public List<OwnerInfo> getAvailableFor() {
        return availableFor;
    }

    public void setAvailableFor(List<OwnerInfo> availableFor) {
        this.availableFor = availableFor;
    }
}
