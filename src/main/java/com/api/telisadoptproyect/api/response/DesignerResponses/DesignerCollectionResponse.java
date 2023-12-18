package com.api.telisadoptproyect.api.response.DesignerResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;

import java.util.Collections;
import java.util.List;

public class DesignerCollectionResponse extends BaseResponse {
    private List<DesignerInfo> designerInfoList;

    public DesignerCollectionResponse(Status status, Integer code){
        super(status, code);
        this.designerInfoList = Collections.emptyList();
    }

    public DesignerCollectionResponse(Status status, Integer code, List<DesignerInfo> designerInfoList){
        super(status, code);
        this.designerInfoList = designerInfoList;
    }

    public List<DesignerInfo> getDesignerInfoList() {
        return designerInfoList;
    }

    public void setDesignerInfoList(List<DesignerInfo> designerInfoList) {
        this.designerInfoList = designerInfoList;
    }
}
