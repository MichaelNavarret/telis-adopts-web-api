package com.api.telisadoptproyect.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class SpecieForm {
    @Id
    private String id;
    private String code;
    private String formUrlImage;
    public SpecieForm() {
        this.id = UUID.randomUUID().toString();
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

    public String getFormUrlImage() {
        return formUrlImage;
    }

    public void setFormUrlImage(String formUrlImage) {
        this.formUrlImage = formUrlImage;
    }
}
