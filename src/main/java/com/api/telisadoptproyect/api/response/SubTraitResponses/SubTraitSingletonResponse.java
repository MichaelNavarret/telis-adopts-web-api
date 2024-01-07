package com.api.telisadoptproyect.api.response.SubTraitResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.SubTrait;

public class SubTraitSingletonResponse extends BaseResponse {
    private SubTraitSingletonInfo subTraitSingletonInfo;

    public SubTraitSingletonResponse(Status status, Integer code, SubTrait subTrait){
        super(status, code);
        this.subTraitSingletonInfo = new SubTraitSingletonInfo(subTrait);
    }

    public SubTraitSingletonInfo getSubTraitSingletonInfo() {
        return subTraitSingletonInfo;
    }

    public void setSubTraitSingletonInfo(SubTraitSingletonInfo subTraitSingletonInfo) {
        this.subTraitSingletonInfo = subTraitSingletonInfo;
    }
}
