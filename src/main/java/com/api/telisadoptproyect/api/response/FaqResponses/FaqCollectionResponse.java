package com.api.telisadoptproyect.api.response.FaqResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Faq;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FaqCollectionResponse extends BaseResponse {

    private List<FaqInfo> faqInfoList;

    public FaqCollectionResponse(){
        super(Status.SUCCESS, 200);
        this.faqInfoList = Collections.emptyList();
    }

    public FaqCollectionResponse(List<Faq> faqList) {
        this();
        this.faqInfoList = faqList.stream().map(FaqInfo::new).collect(Collectors.toList());
    }

    public List<FaqInfo> getFaqInfoList() {
        return faqInfoList;
    }

    public void setFaqInfoList(List<FaqInfo> faqInfoList) {
        this.faqInfoList = faqInfoList;
    }
}
