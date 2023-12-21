package com.api.telisadoptproyect.api.request.SubTraitRequests;

public class SubTraitCreateRequest {
    private String subTraitCharacteristic;
    private String mainTraitId;
    private String adoptId;

    public SubTraitCreateRequest() {
    }

    public String getSubTraitCharacteristic() {
        return subTraitCharacteristic;
    }

    public void setSubTraitCharacteristic(String subTraitCharacteristic) {
        this.subTraitCharacteristic = subTraitCharacteristic;
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
}
