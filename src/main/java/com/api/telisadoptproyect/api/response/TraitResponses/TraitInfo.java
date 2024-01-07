package com.api.telisadoptproyect.api.response.TraitResponses;

import com.api.telisadoptproyect.library.entity.Trait;

import java.util.List;

public class TraitInfo {
    private String id;
    private List<Trait.Rarity> rarities;
    private String trait;
    private String specie;

    public TraitInfo(Trait trait){
        this.id = trait.getId();
        this.rarities = trait.getRarities();
        this.trait = trait.getTrait();
        this.specie = trait.getSpecie().getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public List<Trait.Rarity> getRarities() {
        return rarities;
    }

    public void setRarities(List<Trait.Rarity> rarities) {
        this.rarities = rarities;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }
}
