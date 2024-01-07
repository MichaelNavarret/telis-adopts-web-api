package com.api.telisadoptproyect.api.request.SubTraitRequests;

public class SubTraitCreateRequest {
    private String additionalInfo;
    private String mainTraitId;
    private String adoptId;
    private String rarity;

    public SubTraitCreateRequest() {
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getMainTraitId() {
        return mainTraitId;
    }

    public void setMainTraitId(String mainTraitId) {
        this.mainTraitId = mainTraitId;
    }

    public String getAdoptId() {
        return adoptId;
    }

    public void setAdoptId(String adoptId) {
        this.adoptId = adoptId;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}
