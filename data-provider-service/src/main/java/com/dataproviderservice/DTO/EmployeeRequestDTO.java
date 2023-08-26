package com.dataproviderservice.DTO;

import com.dataproviderservice.Entity.Department;
import com.dataproviderservice.Entity.role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeRequestDTO {
    private int id;
    private String name;
    private String contact;
    private boolean enabled;
    private String emailAddress;
    private String password;
    private Integer department;
    private List<Integer> roles=new ArrayList<>();

}
