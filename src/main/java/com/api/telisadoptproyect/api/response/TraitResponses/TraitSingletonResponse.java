package com.api.telisadoptproyect.api.response.TraitResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Trait;

public class TraitSingletonResponse extends BaseResponse {

    private TraitSingletonInfo traitSingletonInfo;

    public TraitSingletonResponse(Status status, Integer code, Trait trait){
        super(status, code);
        this.traitSingletonInfo = new TraitSingletonInfo(trait);
    }

    public TraitSingletonInfo getTraitSingletonInfo() {
        return traitSingletonInfo;
    }

    public void setTraitSingletonInfo(TraitSingletonInfo traitSingletonInfo) {
        this.traitSingletonInfo = traitSingletonInfo;
    }
}
