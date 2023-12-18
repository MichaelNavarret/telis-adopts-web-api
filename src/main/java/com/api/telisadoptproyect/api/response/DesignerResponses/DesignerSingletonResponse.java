package com.api.telisadoptproyect.api.response.DesignerResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Designer;

public class DesignerSingletonResponse extends BaseResponse {

    private DesignerSingletonInfo designerSingletonInfo;

    public DesignerSingletonResponse(Status status, Integer code, Designer designer) {
        super(status, code);
        this.designerSingletonInfo = new DesignerSingletonInfo(designer);
    }

    public DesignerSingletonInfo getDesignerSingletonInfo() {
        return designerSingletonInfo;
    }

    public void setDesignerSingletonInfo(DesignerSingletonInfo designerSingletonInfo) {
        this.designerSingletonInfo = designerSingletonInfo;
    }
}
