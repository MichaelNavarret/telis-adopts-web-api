package com.api.telisadoptproyect.api.response.AdoptResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;

import java.util.Collections;
import java.util.List;

public class AdoptCollectionResponse extends BaseResponse {
    private List<AdoptInfo> adoptInfoList;

    public AdoptCollectionResponse(Status status, Integer code) {
        super(status, code);
        this.adoptInfoList = Collections.emptyList();
    }

    public AdoptCollectionResponse(Status status, Integer code, List<AdoptInfo> adoptInfoList) {
        super(status, code);
        this.adoptInfoList = adoptInfoList;
    }

    public List<AdoptInfo> getAdoptInfoList() {
        return adoptInfoList;
    }

    public void setAdoptInfoList(List<AdoptInfo> adoptInfoList) {
        this.adoptInfoList = adoptInfoList;
    }
}
