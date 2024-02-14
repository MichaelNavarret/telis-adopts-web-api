package com.api.telisadoptproyect.api.response.OwnerResponses;

import com.api.telisadoptproyect.api.response.RoleResponses.OwnerRoleSummaryInfo;
import com.api.telisadoptproyect.library.entity.Adopt;
import com.api.telisadoptproyect.library.entity.Owner;
import com.api.telisadoptproyect.library.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OwnerInfo {
    private String id;
    private String nickName;
    private String email;
    private boolean status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OwnerRoleSummaryInfo role;
    private String iconUrl;
    private List<String> favoriteAdopts;
    private String discord;
    private String instagram;
    private String twitter;
    private String toyhouse;
    private String devianart;
    private List<String> favoriteCharacters;
    public OwnerInfo(Owner owner){
        this.id = owner.getId();
        this.nickName = owner.getNickName();
        this.email = owner.getEmail();
        this.status = owner.isActive();
        Role role = !owner.getRoles().isEmpty() ? owner.getRoles().iterator().next() : null;
        if(role != null){
            this.role = new OwnerRoleSummaryInfo(role);
        }
        this.iconUrl = owner.getProfileIcon() != null? owner.getProfileIcon().getIconUrl() : null;
        this.favoriteAdopts = owner.getFavorites() != null ? owner.getFavorites().stream().map(Adopt::getId).toList() : null;
        this.discord = owner.getDiscord();
        this.instagram = owner.getInstagram();
        this.twitter = owner.getTwitter();
        this.toyhouse = owner.getToyhouse();
        this.devianart = owner.getDevianart();
        this.favoriteCharacters = owner.getFavoriteCharacters() != null ? owner.getFavoriteCharacters().stream()
                                                                                .sorted(Comparator.comparingInt(Adopt::getFavoriteCharacterIndex))
                                                                                .map(Adopt::getId)
                                                                                .collect(Collectors.toList()) : null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public OwnerRoleSummaryInfo getRole() {
        return role;
    }

    public void setRole(OwnerRoleSummaryInfo role) {
        this.role = role;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public List<String> getFavoriteAdopts() {
        return favoriteAdopts;
    }

    public void setFavoriteAdopts(List<String> favoriteAdopts) {
        this.favoriteAdopts = favoriteAdopts;
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

    public String getToyhouse() {
        return toyhouse;
    }

    public void setToyhouse(String toyhouse) {
        this.toyhouse = toyhouse;
    }

    public String getDevianart() {
        return devianart;
    }

    public void setDevianart(String devianart) {
        this.devianart = devianart;
    }

    public List<String> getFavoriteCharacters() {
        return favoriteCharacters;
    }

    public void setFavoriteCharacters(List<String> favoriteCharacters) {
        this.favoriteCharacters = favoriteCharacters;
    }
}
