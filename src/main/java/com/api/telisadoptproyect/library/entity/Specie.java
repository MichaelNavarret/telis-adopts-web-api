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

    @Lob()
    @Column(name = "traitsInformation", columnDefinition = "LONGBLOB")
    private byte[] traitsInformation;

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

    public byte[] getTraitsInformation() {
        return traitsInformation;
    }

    public void setTraitsInformation(byte[] traitsInformation) {
        this.traitsInformation = traitsInformation;
    }
}
