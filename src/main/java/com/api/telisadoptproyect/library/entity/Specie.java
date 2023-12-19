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
    @OneToMany
    private List<Adopt> adopts;
    @OneToMany
    private List<Trait> traits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mainSpecie_Id")
    private Specie mainSpecie;
    @OneToMany
    private Set<Specie> subSpecies;

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

    public List<Adopt> getAdopts() {
        return adopts;
    }

    public void setAdopts(List<Adopt> adopts) {
        this.adopts = adopts;
    }

    public List<Trait> getTraits() {
        return traits;
    }

    public void setTraits(List<Trait> traits) {
        this.traits = traits;
    }
}
