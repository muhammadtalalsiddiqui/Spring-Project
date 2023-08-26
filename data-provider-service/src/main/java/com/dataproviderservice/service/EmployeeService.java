package com.dataproviderservice.service;

import com.dataproviderservice.DTO.AuthResponseDTO;
import com.dataproviderservice.DTO.EmployeeRequestDTO;
import com.dataproviderservice.DTO.EmployeeResponseDTO;
import com.dataproviderservice.Entity.Employee;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAllEmployees();
    AuthResponseDTO RegisterEmployee(EmployeeRequestDTO employeeRequestDTO);

}
