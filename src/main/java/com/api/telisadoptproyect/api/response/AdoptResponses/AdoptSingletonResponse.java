package com.api.telisadoptproyect.api.response.AdoptResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Adopt;

public class AdoptSingletonResponse extends BaseResponse {
    private AdoptSingletonInfo adoptSingletonInfo;

    public AdoptSingletonResponse(Status status, Integer code, Adopt adopt){
        super(status, code);
        this.adoptSingletonInfo = new AdoptSingletonInfo(adopt);
    }

    public AdoptSingletonInfo getAdoptSingletonInfo() {
        return adoptSingletonInfo;
    }

    public void setAdoptSingletonInfo(AdoptSingletonInfo adoptSingletonInfo) {
        this.adoptSingletonInfo = adoptSingletonInfo;
    }
}
