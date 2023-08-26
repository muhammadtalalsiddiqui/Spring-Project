package com.dataproviderservice.Controller;


import com.dataproviderservice.DTO.AuthResponseDTO;
import com.dataproviderservice.DTO.EmployeeRequestDTO;
import com.dataproviderservice.DTO.EmployeeResponseDTO;
import com.dataproviderservice.Entity.Employee;
import com.dataproviderservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@RequestMapping("/employee")
public class EmployeeController {
@Autowired
    EmployeeService employeeService;

@GetMapping("/getall")
    public List<EmployeeResponseDTO> employee(){
    return employeeService.getAllEmployees();


}
    @PostMapping ("/register")
    public ResponseEntity<AuthResponseDTO> RegisterEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO)
    {
    return ResponseEntity.ok( employeeService.RegisterEmployee(employeeRequestDTO));
    }

}
