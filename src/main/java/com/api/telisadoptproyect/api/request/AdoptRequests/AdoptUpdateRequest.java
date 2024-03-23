package com.api.telisadoptproyect.api.request.AdoptRequests;

import com.api.telisadoptproyect.api.request.SubTraitRequests.SubTraitUpdateRequest;
import com.api.telisadoptproyect.api.response.SubTraitResponses.SubTraitInfo;

import java.util.List;

public class AdoptUpdateRequest {
    private String name;
    private List<SubTraitUpdateRequest> subTraits;
    private String specieId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubTraitUpdateRequest> getSubTraits() {
        return subTraits;
    }
    public void setSubTraits(List<SubTraitUpdateRequest> subTraits) {
        this.subTraits = subTraits;
    }
    public String getSpecieId() {
        return specieId;
    }
    public void setSpecieId(String specieId) {
        this.specieId = specieId;
    }
}
