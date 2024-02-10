package com.api.telisadoptproyect.api.request.OwnerRequests;

import java.util.List;

public class OwnerUpdateRequest {
    private String nickName;
    private String email;
    private String discord;
    private String instagram;
    private String twitter;
    private String devianart;
    private String toyhouse;
    private String iconId;
    private List<String> favoriteAdoptsIds;

    public OwnerUpdateRequest() {
    }

    public List<String> getFavoriteAdoptsIds() {
        return favoriteAdoptsIds;
    }

    public void setFavoriteAdoptsIds(List<String> favoriteAdoptsIds) {
        this.favoriteAdoptsIds = favoriteAdoptsIds;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiscord() {
        return discord;
    }

    public void setDiscord(String discord) {
        this.discord = discord;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getDevianart() {
        return devianart;
    }

    public void setDevianart(String devianart) {
        this.devianart = devianart;
    }

    public String getToyhouse() {
        return toyhouse;
    }

    public void setToyhouse(String toyhouse) {
        this.toyhouse = toyhouse;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }
}
