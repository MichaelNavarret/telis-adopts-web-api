package com.api.telisadoptproyect.api.response.AdoptResponses;

import com.api.telisadoptproyect.api.response.BadgeResponses.BadgeInfo;
import com.api.telisadoptproyect.api.response.SubTraitResponses.SubTraitInfo;
import com.api.telisadoptproyect.library.entity.Adopt;
import com.api.telisadoptproyect.library.entity.Owner;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdoptInfo {
    private String id;
    private String code;
    private String name;
    private String ownerName;
    private String specieName;
    private List<String> designers;
    private Date createdOn;
    private Date boughtOn;
    private Date registeredOn;
    private String rarity;
    private List<SubTraitInfo> traits;
    private String iconUrl;
    private String specieFormId;
    private List<BadgeInfo> badges;
    private int favoriteCharacterIndex;
    public AdoptInfo (Adopt adopt){
        this.id = adopt.getId();
        this.code = adopt.getCode();
        this.name = adopt.getName();
        this.ownerName = adopt.getOwner() != null? adopt.getOwner().getNickName() : null;
        this.createdOn = adopt.getCreatedOn();
        this.boughtOn = adopt.getBoughtOn();
        this.specieName = adopt.getSpecie().getName();
        this.registeredOn = adopt.getRegisteredOn();
        this.designers = adopt.getDesigners() != null ? adopt.getDesigners().stream().map(Owner::getNickName).collect(Collectors.toList()) : null;
        this.rarity = adopt.getRarity().toString();
        this.traits = adopt.getSubTraits() != null? adopt.getSubTraits().stream().map(SubTraitInfo::new).collect(Collectors.toList()) : null;
        this.iconUrl = adopt.getIconUrl();
        this.specieFormId = adopt.getExtraInfo() != null? adopt.getExtraInfo().getId() : null;
        this.badges = adopt.getBadges().isEmpty() ? null : adopt.getBadges().stream().map(BadgeInfo::new).collect(Collectors.toList());
        this.favoriteCharacterIndex = adopt.getFavoriteCharacterIndex();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<String> getDesigners() {
        return designers;
    }

    public void setDesigners(List<String> designers) {
        this.designers = designers;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getBoughtOn() {
        return boughtOn;
    }

    public void setBoughtOn(Date boughtOn) {
        this.boughtOn = boughtOn;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public String getSpecieName() {
        return specieName;
    }

    public void setSpecieName(String specieName) {
        this.specieName = specieName;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public List<SubTraitInfo> getTraits() {
        return traits;
    }

    public void setTraits(List<SubTraitInfo> traits) {
        this.traits = traits;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getSpecieFormId() {
        return specieFormId;
    }

    public void setSpecieFormId(String specieFormId) {
        this.specieFormId = specieFormId;
    }

    public List<BadgeInfo> getBadges() {
        return badges;
    }

    public void setBadges(List<BadgeInfo> badges) {
        this.badges = badges;
    }

    public int getFavoriteCharacterIndex() {
        return favoriteCharacterIndex;
    }

    public void setFavoriteCharacterIndex(int favoriteCharacterIndex) {
        this.favoriteCharacterIndex = favoriteCharacterIndex;
    }
}
