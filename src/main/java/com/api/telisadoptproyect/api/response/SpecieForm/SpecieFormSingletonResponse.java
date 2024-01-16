package com.api.telisadoptproyect.api.response.SpecieForm;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.SpecieForm;

public class SpecieFormSingletonResponse extends BaseResponse {
    private SpecieFormSingletonInfo specieFormSingletonInfo;

    public SpecieFormSingletonResponse(Status status, Integer code, SpecieForm specieForm) {
        super(status, code);
        this.specieFormSingletonInfo = new SpecieFormSingletonInfo(specieForm);
    }

    public SpecieFormSingletonInfo getSpecieFormSingletonInfo() {
        return specieFormSingletonInfo;
    }

    public void setSpecieFormSingletonInfo(SpecieFormSingletonInfo specieFormSingletonInfo) {
        this.specieFormSingletonInfo = specieFormSingletonInfo;
    }
}
