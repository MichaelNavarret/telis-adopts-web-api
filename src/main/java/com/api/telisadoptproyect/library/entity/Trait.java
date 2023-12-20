package com.api.telisadoptproyect.library.entity;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
public class Trait {
    public enum Rarity{
        COMMON,
        UNCOMMON,
        RARE,
        EPIC
    }
    @Id
    private String id;
    private Rarity rarity;
    private String code;
    private String characteristic;

    private String additionalInfo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specie_Id")
    private Specie specie;
    @ManyToMany(mappedBy = "traits")
    private Set<Adopt> adopts;
    @ManyToOne
    @JoinColumn(name = "mainTrait_Id")
    private Trait mainTrait;
    @OneToMany(mappedBy = "mainTrait", cascade = CascadeType.ALL)
    private Set<Trait> subTraits;

    public Trait() {
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

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public Set<Adopt> getAdopts() {
        return adopts;
    }

    public void setAdopts(Set<Adopt> adopts) {
        this.adopts = adopts;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public Trait getMainTrait() {
        return mainTrait;
    }

    public void setMainTrait(Trait mainTrait) {
        this.mainTrait = mainTrait;
    }

    public Set<Trait> getSubTraits() {
        return subTraits;
    }

    public void setSubTraits(Set<Trait> subTraits) {
        this.subTraits = subTraits;
    }
}
