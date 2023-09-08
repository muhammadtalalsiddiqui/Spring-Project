package com.dataproviderservice.DTO;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AuthResponseDTO {
    private String token;
    private String name;
    private String contactNumber;
    private  String department;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public AuthResponseDTO() {
    }
}
