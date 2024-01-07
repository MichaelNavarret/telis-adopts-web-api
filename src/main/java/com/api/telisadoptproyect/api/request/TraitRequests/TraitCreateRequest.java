package com.api.telisadoptproyect.api.request.TraitRequests;

import java.util.List;

public class TraitCreateRequest {
    private String specieId;
    private String trait;
    private List<String> rarities;

    public TraitCreateRequest() {
    }

    public String getSpecieId() {
        return specieId;
    }

    public void setSpecieId(String specieId) {
        this.specieId = specieId;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public List<String> getRarities() {
        return rarities;
    }

    public void setRarities(List<String> rarities) {
        this.rarities = rarities;
    }
}