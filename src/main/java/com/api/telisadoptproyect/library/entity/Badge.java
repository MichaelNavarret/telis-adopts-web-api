package com.api.telisadoptproyect.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Badge {
    @Id
    private String id;
    private String name;
    private String code;
    private String description;
    @ManyToMany(mappedBy = "badges")
    private Set<Adopt> adopts;

    public Badge() {
        this.id = UUID.randomUUID().toString();
        this.adopts = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Adopt> getAdopts() {
        return adopts;
    }

    public void setAdopts(Set<Adopt> adopts) {
        this.adopts = adopts;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
