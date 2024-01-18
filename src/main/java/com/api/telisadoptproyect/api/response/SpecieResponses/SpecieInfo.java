package com.api.telisadoptproyect.api.response.SpecieResponses;

import com.api.telisadoptproyect.api.response.SpecieForm.SpecieFormInfo;
import com.api.telisadoptproyect.library.entity.Specie;

import java.util.List;

public class SpecieInfo {
    private String id;
    private String code;
    private String name;
    private String traitSheetUrl;
    private String logoUrl;
    private String masterListBannerUrl;
    private List<SpecieFormInfo> specieFormInfoList;
    public SpecieInfo(Specie specie) {
        this.id = specie.getId();
        this.code = specie.getCode();
        this.name = specie.getName();
        this.traitSheetUrl = specie.getTraitSheetUrl();
        this.logoUrl = specie.getLogoUrl();
        this.masterListBannerUrl = specie.getMasterListBannerUrl();
        this.specieFormInfoList = specie.getExtraInfoList() == null || specie.getExtraInfoList().isEmpty() ?
                null : specie.getExtraInfoList().stream().map(SpecieFormInfo::new).toList();
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

    public String getTraitSheetUrl() {
        return traitSheetUrl;
    }
    public void setTraitSheetUrl(String traitSheetUrl) {
        this.traitSheetUrl = traitSheetUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getMasterListBannerUrl() {
        return masterListBannerUrl;
    }

    public void setMasterListBannerUrl(String masterListBannerUrl) {
        this.masterListBannerUrl = masterListBannerUrl;
    }

    public List<SpecieFormInfo> getSpecieFormInfoList() {
        return specieFormInfoList;
    }

    public void setSpecieFormInfoList(List<SpecieFormInfo> specieFormInfoList) {
        this.specieFormInfoList = specieFormInfoList;
    }
}
