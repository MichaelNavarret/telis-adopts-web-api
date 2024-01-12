package com.api.telisadoptproyect.api.response.TraitResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Trait;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TraitCollectionResponse extends BaseResponse {

    private List<TraitInfo> traitInfoList;

    public TraitCollectionResponse(){
        super(Status.SUCCESS, 200);
        this.traitInfoList = Collections.emptyList();
    }

    public TraitCollectionResponse(Page<Trait> traits){
        this();
        this.traitInfoList = traits.stream().map(TraitInfo::new).collect(Collectors.toList());
    }
    public TraitCollectionResponse(Status status, Integer code, List<Trait> traits){
        super(status, code);
        this.traitInfoList = traits.stream().map(TraitInfo::new).collect(Collectors.toList());
    }

    public List<TraitInfo> getTraitInfoList() {
        return traitInfoList;
    }

    public void setTraitInfoList(List<TraitInfo> traitInfoList) {
        this.traitInfoList = traitInfoList;
    }
}
