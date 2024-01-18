package com.api.telisadoptproyect.api.response.SpecieForm;

import com.api.telisadoptproyect.library.entity.SpecieForm;

public class SpecieFormInfo {
    private String id;
    private String code;
    private String imageUrl;

    public SpecieFormInfo(SpecieForm specieForm) {
        this.id = specieForm.getId();
        this.code = specieForm.getCode();
        this.imageUrl = specieForm.getFormUrlImage();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
