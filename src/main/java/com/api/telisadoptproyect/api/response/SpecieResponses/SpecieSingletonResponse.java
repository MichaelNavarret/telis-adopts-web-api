package com.api.telisadoptproyect.api.response.SpecieResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Specie;

public class SpecieSingletonResponse extends BaseResponse {
    private SpecieSingletonInfo specieSingletonInfo;

    public SpecieSingletonResponse(Status status, Integer code, Specie specie) {
        super(status, code);
        this.specieSingletonInfo = new SpecieSingletonInfo(specie);
    }

    public SpecieSingletonInfo getSpecieSingletonInfo() {
        return specieSingletonInfo;
    }

    public void setSpecieSingletonInfo(SpecieSingletonInfo specieSingletonInfo) {
        this.specieSingletonInfo = specieSingletonInfo;
    }
}
