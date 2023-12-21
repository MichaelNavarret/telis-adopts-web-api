package com.api.telisadoptproyect.library.entity;

import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@QueryEntity
@Table(indexes = @Index(columnList = "creationType"))
public class Adopt {
    public enum CreationType{
        PREMADE,
        CUSTOM,
        MYO,
        GUEST_ARTIST
    }
    @Id
    private String id;
    private String code;
    @Enumerated(EnumType.STRING)
    private CreationType creationType;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_Id")
    private Owner owner;
    @ManyToMany
    @JoinTable(name="rel_adopt_designer", joinColumns = @JoinColumn(name = "adoptId"),
                inverseJoinColumns = @JoinColumn(name = "designerId"))
    private Set<Designer> designers;
    @OneToMany(mappedBy = "adopt", cascade = CascadeType.ALL)
    private Set<SubTrait> subTraits;
    private Date createdOn;
    private Date boughtOn;
    private Date registeredOn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specie_Id")
    private Specie specie;

    public Adopt() {
        this.id = UUID.randomUUID().toString();
        this.createdOn = new Date();
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Set<Designer> getDesigners() {
        return designers;
    }

    public void setDesigners(Set<Designer> designers) {
        this.designers = designers;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getBoughtOn() {
        return boughtOn;
    }

    public void setBoughtOn(Date boughtOn) {
        this.boughtOn = boughtOn;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Specie getSpecie() {
        return specie;
    }

    public void setSpecie(Specie specie) {
        this.specie = specie;
    }

    public CreationType getCreationType() {
        return creationType;
    }

    public void setCreationType(CreationType creationType) {
        this.creationType = creationType;
    }

    public Set<SubTrait> getSubTraits() {
        return subTraits;
    }

    public void setSubTraits(Set<SubTrait> subTraits) {
        this.subTraits = subTraits;
    }
}
