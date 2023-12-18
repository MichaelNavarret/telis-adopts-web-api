package com.api.telisadoptproyect.api.response.AdoptResponses;

import com.api.telisadoptproyect.library.entity.Adopt;
import com.api.telisadoptproyect.library.entity.Designer;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdoptInfo {
    private String id;
    private String code;
    private String name;
    private String ownerName;
    private List<String> designers;
    private Date createdOn;
    private Date boughtOn;
    private Date registeredOn;

    public AdoptInfo (Adopt adopt){
        this.id = adopt.getId();
        this.code = adopt.getCode();
        this.name = adopt.getName();
        this.ownerName = adopt.getOwner().getName();
        this.designers = adopt.getDesigners().stream().map(Designer::getName).collect(Collectors.toList());
        this.createdOn = adopt.getCreatedOn();
        this.boughtOn = adopt.getBoughtOn();
        this.registeredOn = adopt.getRegisteredOn();
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
}
