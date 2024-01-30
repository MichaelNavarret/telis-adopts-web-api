package com.api.telisadoptproyect.api.response.IconResponses;

import com.api.telisadoptproyect.api.response.BaseResponse;
import com.api.telisadoptproyect.library.entity.Icon;

public class IconSingletonResponse extends BaseResponse {
    private IconSingletonInfo iconSingletonInfo;

    public IconSingletonResponse(Status status, Integer code, Icon icon){
        super(status, code);
        this.iconSingletonInfo = new IconSingletonInfo(icon);
    }

    public IconSingletonInfo getIconSingletonInfo() {
        return iconSingletonInfo;
    }
    public void setIconSingletonInfo(IconSingletonInfo iconSingletonInfo) {
        this.iconSingletonInfo = iconSingletonInfo;
    }
}
