package com.api.telisadoptproyect.api.request.TraitRequests;

import java.util.List;

public class TraitCreateRequest {
    private String specieId;
    private String characteristic;
    private String code;
    private String rarity;

    public TraitCreateRequest() {
    }

    public String getSpecieId() {
        return specieId;
    }

    public void setSpecieId(String specieId) {
        this.specieId = specieId;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}