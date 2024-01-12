package com.api.telisadoptproyect.api.response.SpecieResponses;

import com.api.telisadoptproyect.library.entity.Specie;

public class SpecieInfo {
    private String id;
    private String code;
    private String name;
    private byte[] traitsInformation;
    public SpecieInfo(Specie specie) {
        this.id = specie.getId();
        this.code = specie.getCode();
        this.name = specie.getName();
        this.traitsInformation = specie.getTraitsInformation() != null? specie.getTraitsInformation(): null;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getTraitsInformation() {
        return traitsInformation;
    }

    public void setTraitsInformation(byte[] traitsInformation) {
        this.traitsInformation = traitsInformation;
    }
}
