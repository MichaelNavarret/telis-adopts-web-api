package com.api.telisadoptproyect.api.response.SubTraitResponses;

import com.api.telisadoptproyect.library.entity.SubTrait;

public class SubTraitInfo {
    private String id;
    private String mainTrait;
    private String additionalInfo;
    private String rarity;

    public SubTraitInfo(SubTrait subTrait){
        this.id = subTrait.getId();
        this.mainTrait = subTrait.getMainTrait().getTrait();
        this.additionalInfo = subTrait.getAdditionalInfo();
        this.rarity = subTrait.getRarity().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainTrait() {
        return mainTrait;
    }

    public void setMainTrait(String mainTrait) {
        this.mainTrait = mainTrait;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}
