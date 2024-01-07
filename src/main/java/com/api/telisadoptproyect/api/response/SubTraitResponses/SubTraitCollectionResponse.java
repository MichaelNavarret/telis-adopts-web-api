package com.api.telisadoptproyect.api.response.SubTraitResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.SubTrait;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SubTraitCollectionResponse extends BaseResponse {
    private List<SubTraitInfo> subTraitInfoList;

    public SubTraitCollectionResponse(){
        super(Status.SUCCESS, 200);
        this.subTraitInfoList = Collections.emptyList();
    }

    public SubTraitCollectionResponse(Page<SubTrait> subTraits){
        this();
        this.subTraitInfoList = subTraits.stream().map(SubTraitInfo::new).collect(Collectors.toList());
    }

    public List<SubTraitInfo> getSubTraitInfoList() {
        return subTraitInfoList;
    }

    public void setSubTraitInfoList(List<SubTraitInfo> subTraitInfoList) {
        this.subTraitInfoList = subTraitInfoList;
    }
}
