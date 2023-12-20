package com.api.telisadoptproyect.library.entity;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
public class SubTrait {
    private String id;
    private String subTraitCharacteristic;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mainTait_Id")
    private Trait mainTrait;
    @ManyToMany(mappedBy = "subTraits")
    private Set<Adopt> adopts;

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

    public Set<Adopt> getAdopts() {
        return adopts;
    }

    public void setAdopts(Set<Adopt> adopts) {
        this.adopts = adopts;
    }
}
