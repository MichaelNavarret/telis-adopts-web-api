package com.api.telisadoptproyect.library.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class SubTrait {
    public enum Rarity{
        COMMON,
        UNCOMMON,
        RARE,
        EPIC
    }
    @Id
    private String id;
    private String subTraitCharacteristic;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mainTait_Id")
    private Trait mainTrait;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adopt_Id")
    private Adopt adopt;
    private Rarity rarity;
    public SubTrait(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubTraitCharacteristic() {
        return subTraitCharacteristic;
    }

    public void setSubTraitCharacteristic(String subTraitCharacteristic) {
        this.subTraitCharacteristic = subTraitCharacteristic;
    }

    public Trait getMainTrait() {
        return mainTrait;
    }

    public void setMainTrait(Trait mainTrait) {
        this.mainTrait = mainTrait;
    }

    public Adopt getAdopt() {
        return adopt;
    }

    public void setAdopt(Adopt adopt) {
        this.adopt = adopt;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }
}
