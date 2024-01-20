package com.api.telisadoptproyect.api.response.FaqResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Faq;

public class FaqSingletonResponse extends BaseResponse {
    private FaqSingletonInfo faqSingletonInfo;

    public FaqSingletonResponse(Status status, Integer code, Faq faq) {
        super(status, code);
        this.faqSingletonInfo = new FaqSingletonInfo(faq);
    }

    public FaqSingletonInfo getFaqSingletonInfo() {
        return faqSingletonInfo;
    }

    public void setFaqSingletonInfo(FaqSingletonInfo faqSingletonInfo) {
        this.faqSingletonInfo = faqSingletonInfo;
    }
}
