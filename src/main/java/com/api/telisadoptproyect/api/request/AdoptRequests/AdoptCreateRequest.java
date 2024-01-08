package com.api.telisadoptproyect.api.request.AdoptRequests;

import com.api.telisadoptproyect.api.request.OwnerRequests.OwnerDesignerCreateRequest;
import com.api.telisadoptproyect.api.request.SubTraitRequests.SubTraitCreateRequest;
import com.api.telisadoptproyect.library.entity.Adopt;

import java.util.List;

public class AdoptCreateRequest {
    private String name;
    private String ownerId;
    private boolean notRegisteredOwner;
    private List<OwnerDesignerCreateRequest> designerIds;
    private List<SubTraitCreateRequest> subTraits;
    private String specieId;
    private String creationType;

    public AdoptCreateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public List<OwnerDesignerCreateRequest> getDesignerIds() {
        return designerIds;
    }

    public void setDesignerIds(List<OwnerDesignerCreateRequest> designerIds) {
        this.designerIds = designerIds;
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

    public String getCreationType() {
        return creationType;
    }

    public void setCreationType(String creationType) {
        this.creationType = creationType;
    }

    public boolean isNotRegisteredOwner() {
        return notRegisteredOwner;
    }

    public void setNotRegisteredOwner(boolean notRegisteredOwner) {
        this.notRegisteredOwner = notRegisteredOwner;
    }
}
