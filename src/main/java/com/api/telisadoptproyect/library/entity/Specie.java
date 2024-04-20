package com.api.telisadoptproyect.library.entity;

import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@QueryEntity
public class Specie {
    @Id
    private String id;
    private String code;
    private String name;
    @ManyToOne
    @JoinColumn(name = "mainSpecie_Id")
    private Specie mainSpecie;
    @OneToMany(mappedBy = "mainSpecie", cascade = CascadeType.ALL)
    private Set<Specie> subSpecies;
    private String traitSheetUrl;
    private String logoUrl;
    private String masterListBannerUrl;
    @OneToMany
    @JoinColumn(name = "specie_Id")
    private Set<SpecieForm> extraInfoList;
    @Column(columnDefinition = "TEXT")
    private String history;
    private String guideSheetUrl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "specie")
    private List<Faq> faqs;
    private String characterUrl;

    public Specie() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specie getMainSpecie() {
        return mainSpecie;
    }

    public void setMainSpecie(Specie mainSpecie) {
        this.mainSpecie = mainSpecie;
    }

    public Set<Specie> getSubSpecies() {
        return subSpecies;
    }

    public void setSubSpecies(Set<Specie> subSpecies) {
        this.subSpecies = subSpecies;
    }

    public String getTraitSheetUrl() {
        return traitSheetUrl;
    }

    public void setTraitSheetUrl(String traitSheetUrl) {
        this.traitSheetUrl = traitSheetUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getMasterListBannerUrl() {
        return masterListBannerUrl;
    }

    public void setMasterListBannerUrl(String masterListBannerUrl) {
        this.masterListBannerUrl = masterListBannerUrl;
    }

    public Set<SpecieForm> getExtraInfoList() {
        return extraInfoList;
    }

    public void setExtraInfoList(Set<SpecieForm> extraInfoList) {
        this.extraInfoList = extraInfoList;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getGuideSheetUrl() {
        return guideSheetUrl;
    }

    public void setGuideSheetUrl(String guideSheetUrl) {
        this.guideSheetUrl = guideSheetUrl;
    }

    public List<Faq> getFaqs() {
        return faqs;
    }

    public void setFaqs(List<Faq> faqs) {
        this.faqs = faqs;
    }

    public String getCharacterUrl() {
        return characterUrl;
    }

    public void setCharacterUrl(String characterUrl) {
        this.characterUrl = characterUrl;
    }
}
