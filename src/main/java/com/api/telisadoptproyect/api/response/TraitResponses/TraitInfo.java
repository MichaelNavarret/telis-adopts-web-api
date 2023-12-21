package com.api.telisadoptproyect.api.response.TraitResponses;

import com.api.telisadoptproyect.library.entity.Trait;

public class TraitInfo {
    private String id;
    private Trait.Rarity rarity;
    private String code;
    private String characteristic;
    private String additionalInfo;
    private String specie;

    public TraitInfo(Trait trait){
        this.id = trait.getId();
        this.rarity = trait.getRarity();
        this.code = trait.getCode();
        this.characteristic = trait.getCharacteristic();
        this.specie = trait.getSpecie().getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Trait.Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Trait.Rarity rarity) {
        this.rarity = rarity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }
}
