package com.api.telisadoptproyect.api.response.AdoptResponses;

import com.api.telisadoptproyect.api.response.BadgeResponses.BadgeInfo;
import com.api.telisadoptproyect.api.response.OwnerResponses.OwnerInfo;
import com.api.telisadoptproyect.api.response.SubTraitResponses.SubTraitInfo;
import com.api.telisadoptproyect.library.entity.Adopt;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdoptInfo {
    private String id;
    private String code;
    private String name;
    private String ownerName;
    private String specieName;
    private List<OwnerInfo> designers;
    private Date createdOn;
    private Date boughtOn;
    private Date registeredOn;
    private String rarity;
    private List<SubTraitInfo> traits;
    private String iconUrl;
    private String specieFormId;
    private BadgeInfo badge;
    private int favoriteCharacterIndex;
    private String ownerId;
    private String specieCode;
    private String specieFormUrl;
    private String specieId;
    private String creationType;
    public AdoptInfo (Adopt adopt){
        this.id = adopt.getId();
        this.code = adopt.getCode();
        this.name = adopt.getName();
        this.ownerName = adopt.getOwner() != null? adopt.getOwner().getNickName() : null;
        this.createdOn = adopt.getCreatedOn();
        this.boughtOn = adopt.getBoughtOn();
        this.specieName = adopt.getSpecie().getName();
        this.registeredOn = adopt.getRegisteredOn();
        this.designers = adopt.getDesigners() != null? adopt.getDesigners().stream().map(OwnerInfo::new)
                .sorted(Comparator.comparing(OwnerInfo::getNickName)).collect(Collectors.toList()) : null;
        this.rarity = adopt.getRarity().toString();
        this.traits = adopt.getSubTraits() != null? adopt.getSubTraits().stream().map(SubTraitInfo::new).collect(Collectors.toList()) : null;
        this.iconUrl = adopt.getIconUrl();
        this.specieFormId = adopt.getExtraInfo() != null? adopt.getExtraInfo().getId() : null;
        this.badge = adopt.getBadge() != null ? new BadgeInfo(adopt.getBadge()) : null;
        this.favoriteCharacterIndex = adopt.getFavoriteCharacterIndex();
        this.ownerId = adopt.getOwner() != null ? adopt.getOwner().getId() : null;
        this.specieCode = adopt.getSpecie().getCode();
        this.specieFormUrl = adopt.getExtraInfo() != null ? adopt.getExtraInfo().getFormUrlImage() : null;
        this.specieId = adopt.getSpecie().getId();
        this.creationType = adopt.getCreationType().toString();
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

    public List<OwnerInfo> getDesigners() {
        return designers;
    }

    public void setDesigners(List<OwnerInfo> designers) {
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

    public int getFavoriteCharacterIndex() {
        return favoriteCharacterIndex;
    }

    public void setFavoriteCharacterIndex(int favoriteCharacterIndex) {
        this.favoriteCharacterIndex = favoriteCharacterIndex;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getSpecieCode() {
        return specieCode;
    }

    public void setSpecieCode(String specieCode) {
        this.specieCode = specieCode;
    }

    public BadgeInfo getBadge() {
        return badge;
    }

    public void setBadge(BadgeInfo badge) {
        this.badge = badge;
    }

    public String getSpecieFormUrl() {
        return specieFormUrl;
    }

    public void setSpecieFormUrl(String specieFormUrl) {
        this.specieFormUrl = specieFormUrl;
    }

    public String getSpecieId() {
        return specieId;
    }

    public void setSpecieId(String specieId) {
        this.specieId = specieId;
    }

    public String getCreationType() {
        return creationType;
    }

    public void setCreationType(String creationType) {
        this.creationType = creationType;
    }
}
