package com.dataproviderservice.Entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {
    @Id
@GeneratedValue
@Column(name = "id")
    private int id;
    private String name;
    private Boolean enabled;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    private List<Employee> employees=new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
