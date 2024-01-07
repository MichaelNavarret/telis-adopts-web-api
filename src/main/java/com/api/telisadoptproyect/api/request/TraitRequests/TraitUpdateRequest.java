package com.api.telisadoptproyect.api.request.TraitRequests;

import java.util.List;

public class TraitUpdateRequest {
    private String trait;
    private List<String> rarities;

    public TraitUpdateRequest() {
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
