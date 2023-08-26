package com.dataproviderservice.DTO;

import com.dataproviderservice.Entity.Department;

public class departmentResponseDTO {
    private String name;
    private int id;
    private Boolean enabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public departmentResponseDTO(Department department) {
        this.name = department.getName();
        this.id = department.getId();
        this.enabled = department.getEnabled();
    }
}
