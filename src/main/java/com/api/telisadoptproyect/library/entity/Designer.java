package com.api.telisadoptproyect.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Set;
import java.util.UUID;

@Entity
public class Designer {
    @Id
    private String id;
    private String name;

    @ManyToMany(mappedBy = "designers")
    private Set<Adopt> adopts;

    public Designer() {
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

    public Set<Adopt> getAdopts() {
        return adopts;
    }

    public void setAdopts(Set<Adopt> adopts) {
        this.adopts = adopts;
    }
}
