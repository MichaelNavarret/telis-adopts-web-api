package com.api.telisadoptproyect.api.response.AdoptResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Adopt;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AdoptCollectionResponse extends BaseResponse {
    private List<AdoptInfo> adoptInfoList;

    public AdoptCollectionResponse() {
        super(Status.SUCCESS, 200);
        this.adoptInfoList = Collections.emptyList();
    }

    public AdoptCollectionResponse( Page<Adopt> adopts) {
        this();
        this.adoptInfoList = adopts.stream().map(AdoptInfo::new).collect(Collectors.toList());
    }

    public List<AdoptInfo> getAdoptInfoList() {
        return adoptInfoList;
    }

    public void setAdoptInfoList(List<AdoptInfo> adoptInfoList) {
        this.adoptInfoList = adoptInfoList;
    }
}
