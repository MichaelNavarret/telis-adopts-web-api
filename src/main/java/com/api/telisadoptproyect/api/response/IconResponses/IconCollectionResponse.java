package com.api.telisadoptproyect.api.response.IconResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Icon;

import java.util.Collections;
import java.util.List;

public class IconCollectionResponse extends BaseResponse {
    private List<IconInfo> iconInfoList;

    public IconCollectionResponse(Status status, Integer code){
        super(status, code);
        this.iconInfoList = Collections.emptyList();
    }

    public IconCollectionResponse(Status status, Integer code, List<Icon> icons){
        super(status, code);
        this.iconInfoList = icons.stream().map(IconInfo::new).toList();
    }

    public void setIconInfoList(List<IconInfo> iconInfoList) {
        this.iconInfoList = iconInfoList;
    }

    public List<IconInfo> getIconInfoList() {
        return iconInfoList;
    }
}
