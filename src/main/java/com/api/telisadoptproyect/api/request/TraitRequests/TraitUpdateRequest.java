package com.api.telisadoptproyect.api.request.TraitRequests;

public class TraitUpdateRequest {
    private String characteristic;
    private String code;
    private String rarity;

    public TraitUpdateRequest() {
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
