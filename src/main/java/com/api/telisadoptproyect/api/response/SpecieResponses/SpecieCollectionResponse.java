package com.api.telisadoptproyect.api.response.SpecieResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Specie;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SpecieCollectionResponse extends BaseResponse {
    private List<SpecieInfo> specieInfoList;

    public SpecieCollectionResponse(Status status, Integer code) {
        super(status, code);
        this.specieInfoList = Collections.emptyList();
    }

    public SpecieCollectionResponse(Status status, Integer code, List<Specie> species) {
        super(status, code);
        this.specieInfoList = species.stream().map(SpecieInfo::new).collect(Collectors.toList());
    }

    public List<SpecieInfo> getSpecieInfoList() {
        return specieInfoList;
    }

    public void setSpecieInfoList(List<SpecieInfo> specieInfoList) {
        this.specieInfoList = specieInfoList;
    }
}
