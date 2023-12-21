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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specie_Id")
    private Specie specie;

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

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }
}
