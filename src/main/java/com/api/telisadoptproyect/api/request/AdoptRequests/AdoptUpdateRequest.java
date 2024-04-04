package com.api.telisadoptproyect.api.request.AdoptRequests;

import com.api.telisadoptproyect.api.request.SubTraitRequests.SubTraitCreateRequest;

import java.util.List;

public class AdoptUpdateRequest {
    private String name;
    private List<SubTraitCreateRequest> subTraits;
    private String specieId;
    private String badgeId;
    private String specieFormId;
    private String createdOn;
    private String ownerId;
    private List<String> designerIds;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubTraitCreateRequest> getSubTraits() {
        return subTraits;
    }
    public void setSubTraits(List<SubTraitCreateRequest> subTraits) {
        this.subTraits = subTraits;
    }
    public String getSpecieId() {
        return specieId;
    }
    public void setSpecieId(String specieId) {
        this.specieId = specieId;
    }
    public String getBadgeId() {
        return badgeId;
    }
    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }
    public String getSpecieFormId() {
        return specieFormId;
    }
    public void setSpecieFormId(String specieFormId) {
        this.specieFormId = specieFormId;
    }
    public String getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
    public String getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getDesignerIds() {
        return designerIds;
    }

    public void setDesignerIds(List<String> designerIds) {
        this.designerIds = designerIds;
    }
}
