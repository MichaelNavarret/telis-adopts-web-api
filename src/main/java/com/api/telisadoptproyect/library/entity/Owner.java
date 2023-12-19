package com.api.telisadoptproyect.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
public class Owner {
    @Id
    private String id;
    private String name;
    @OneToMany
    private List<Adopt> adopts;

    public Owner() {
        this.id = UUID.randomUUID().toString();
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

    public List<Adopt> getAdopts() {
        return adopts;
    }

    public void setAdopts(List<Adopt> adopts) {
        this.adopts = adopts;
    }
}
