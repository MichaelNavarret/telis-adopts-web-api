package com.api.telisadoptproyect.api.response.OwnerResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;

import java.util.Collections;
import java.util.List;

public class OwnerCollectionResponse extends BaseResponse {
    private List<OwnerInfo> ownerInfoList;

    public OwnerCollectionResponse(Status status, Integer code){
        super(status, code);
        this.ownerInfoList = Collections.emptyList();
    }

    public OwnerCollectionResponse(Status status, Integer code, List<OwnerInfo> ownerInfoList){
        super(status, code);
        this.ownerInfoList = ownerInfoList;
    }

    public List<OwnerInfo> getOwnerInfoList() {
        return ownerInfoList;
    }

    public void setOwnerInfoList(List<OwnerInfo> ownerInfoList) {
        this.ownerInfoList = ownerInfoList;
    }
}
