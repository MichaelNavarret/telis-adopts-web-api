package com.api.telisadoptproyect.api.response.TraitResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;

import java.util.Collections;
import java.util.List;

public class TraitCollectionResponse extends BaseResponse {

    private List<TraitInfo> traitInfoList;

    public TraitCollectionResponse(Status status, Integer code){
        super(status, code);
        this.traitInfoList = Collections.emptyList();
    }

    public TraitCollectionResponse(Status status, Integer code, List<TraitInfo> traitInfoList){
        super(status, code);
        this.traitInfoList = traitInfoList;
    }

    public List<TraitInfo> getTraitInfoList() {
        return traitInfoList;
    }

    public void setTraitInfoList(List<TraitInfo> traitInfoList) {
        this.traitInfoList = traitInfoList;
    }
}
