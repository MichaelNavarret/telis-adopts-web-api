package com.api.telisadoptproyect.api.response.OwnerResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Owner;

import java.util.Collections;
import java.util.List;

public class OwnerCollectionResponse extends BaseResponse {
    private List<OwnerInfo> ownerInfoList;

    public OwnerCollectionResponse(Status status, Integer code){
        super(status, code);
        this.ownerInfoList = Collections.emptyList();
    }

    public OwnerCollectionResponse(Status status, Integer code, List<Owner> owners){
        super(status, code);
        this.ownerInfoList = owners.stream().map(OwnerInfo::new).toList();
    }

    public List<OwnerInfo> getOwnerInfoList() {
        return ownerInfoList;
    }

    public void setOwnerInfoList(List<OwnerInfo> ownerInfoList) {
        this.ownerInfoList = ownerInfoList;
    }
}
