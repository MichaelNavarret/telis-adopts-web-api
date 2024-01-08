package com.api.telisadoptproyect.api.response.AdoptResponses;

import com.api.telisadoptproyect.library.entity.Adopt;

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
    public AdoptInfo (Adopt adopt){
        this.id = adopt.getId();
        this.code = adopt.getCode();
        this.name = adopt.getName();
        this.ownerName = adopt.getOwner() != null? adopt.getOwner().getNickName() : null;
        this.createdOn = adopt.getCreatedOn();
        this.boughtOn = adopt.getBoughtOn();
        this.specieName = adopt.getSpecie().getName();
        this.registeredOn = adopt.getRegisteredOn();
        this.rarity = adopt.getRarity().toString();
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
}
