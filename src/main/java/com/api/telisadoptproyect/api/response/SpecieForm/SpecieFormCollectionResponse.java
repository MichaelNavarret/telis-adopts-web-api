package com.api.telisadoptproyect.api.response.SpecieForm;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.SpecieForm;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SpecieFormCollectionResponse extends BaseResponse {
    private List<SpecieFormInfo> specieFormInfoList;

    public SpecieFormCollectionResponse(){
        super(Status.SUCCESS, 200);
        this.specieFormInfoList = Collections.emptyList();
    }

    public SpecieFormCollectionResponse(List<SpecieForm> specieForms) {
        this();
        this.specieFormInfoList = specieForms.stream().map(SpecieFormInfo::new).collect(Collectors.toList());
    }

    public List<SpecieFormInfo> getSpecieFormInfoList() {
        return specieFormInfoList;
    }

    public void setSpecieFormInfoList(List<SpecieFormInfo> specieFormInfoList) {
        this.specieFormInfoList = specieFormInfoList;
    }
}
