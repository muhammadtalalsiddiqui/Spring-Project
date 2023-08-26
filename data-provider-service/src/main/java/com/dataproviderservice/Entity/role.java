package com.dataproviderservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "role")
public class role {
    @Id
    @GeneratedValue
    @Column(name = "id")
    

    private int id;
    @NotBlank
    @Column(name = "name")
    private String name;
    @Column(name = "description")

    private String description;
    @Column(name = "code")

    private String code;
    @Column(name = "enabled")

    private Boolean enabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
