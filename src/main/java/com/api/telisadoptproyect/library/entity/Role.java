package com.api.telisadoptproyect.library.entity;

import jakarta.persistence.*;

import java.security.Permissions;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Role {
    @Id
    private String id;
    private String name;
    private boolean active;
    @ManyToMany
    @JoinTable(name = "rel_role_permission", joinColumns = @JoinColumn(name = "roleId"),
                inverseJoinColumns = @JoinColumn(name = "permissionId"))
    private Set<Permission> permissions;
    @ManyToMany(mappedBy = "roles")
    private Set<Owner> owners;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Date createdOn;
    private Date updatedOn;

    public Role() {
        this.id = UUID.randomUUID().toString();
        this.active = true;
        this.owners = new HashSet<>();
        createdOn = new Date();
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
