package com.api.telisadoptproyect.library.entity;

import com.querydsl.core.annotations.QueryEntity;
import com.querydsl.core.annotations.QueryInit;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@QueryEntity
@Table(indexes = {@Index(columnList = "creationType"), @Index(columnList = "rarity")})
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
    @QueryInit("nickName")
    private Owner owner;
    @ManyToMany
    @JoinTable(name="rel_owner_designer", joinColumns = @JoinColumn(name = "adoptId"),
                inverseJoinColumns = @JoinColumn(name = "designerId"))
    private Set<Owner> designers;
    @OneToMany(mappedBy = "adopt", cascade = CascadeType.ALL)
    private Set<SubTrait> subTraits;
    private Date createdOn;
    private Date boughtOn;
    private Date registeredOn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specie_Id")
    private Specie specie;
    @Enumerated(EnumType.STRING)
    private Trait.Rarity rarity;
    private String iconUrl;

    @ManyToOne
    @JoinColumn(name = "extra_info_Id")
    private SpecieForm extraInfo;

    @ManyToOne
    @JoinColumn(name = "badge_Id")
    private Badge badge;
    private String toyhouseLink;

    private int favoriteCharacterIndex;
    private boolean active;
    public Adopt() {
        this.id = UUID.randomUUID().toString();
        this.createdOn = new Date();
        this.favoriteCharacterIndex = 0;
        this.active = true;
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

    public Trait.Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Trait.Rarity rarity) {
        this.rarity = rarity;
    }

    public Set<Owner> getDesigners() {
        return designers;
    }

    public void setDesigners(Set<Owner> designers) {
        this.designers = designers;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public SpecieForm getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(SpecieForm extraInfo) {
        this.extraInfo = extraInfo;
    }

    public int getFavoriteCharacterIndex() {
        return favoriteCharacterIndex;
    }

    public void setFavoriteCharacterIndex(int favoriteCharacterIndex) {
        this.favoriteCharacterIndex = favoriteCharacterIndex;
    }
    public Badge getBadge() {
        return badge;
    }
    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public String getToyhouseLink() {
        return toyhouseLink;
    }

    public void setToyhouseLink(String toyhouseLink) {
        this.toyhouseLink = toyhouseLink;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
