package com.dataproviderservice.DTO;

import com.dataproviderservice.Entity.Employee;
import com.dataproviderservice.Entity.role;

import java.util.List;

public class EmployeeResponseDTO {
    private int id;
    private String name;
    private String contact;
    private boolean enabled;
    private String emailAddress;
    private departmentResponseDTO department;
    private List<role> roles;

    public List<role> getRoles() {
        return roles;
    }

    public void setRoles(List<role> roles) {
        this.roles = roles;
    }

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public departmentResponseDTO getDepartment() {
        return department;
    }

    public void setDepartment(departmentResponseDTO department) {
        this.department = department;
    }

    public EmployeeResponseDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.contact = employee.getContact();
        this.enabled = employee.isEnabled();
        this.emailAddress = employee.getEmailAddress();
        this.roles=employee.getRoles();
        departmentResponseDTO responseDTO=new departmentResponseDTO(employee.getDepartment());

        this.department =responseDTO;
    }
}
