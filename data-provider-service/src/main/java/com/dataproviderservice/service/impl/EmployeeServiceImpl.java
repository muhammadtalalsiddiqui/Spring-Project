package com.dataproviderservice.service.impl;

import com.dataproviderservice.DTO.AuthResponseDTO;
import com.dataproviderservice.DTO.EmployeeRequestDTO;
import com.dataproviderservice.DTO.EmployeeResponseDTO;
import com.dataproviderservice.Entity.Department;
import com.dataproviderservice.Entity.Employee;
import com.dataproviderservice.Entity.role;
import com.dataproviderservice.Repository.DepartmentRepository;
import com.dataproviderservice.Repository.EmployeeRepository;
import com.dataproviderservice.config.JwtService;
import com.dataproviderservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    JwtService jwtService;

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<EmployeeResponseDTO> list=new ArrayList<>();
       List<Employee>employees= employeeRepository.findAll();
       employees.stream().forEach(emp->{
           EmployeeResponseDTO responseDTO=new EmployeeResponseDTO(emp);
           list.add(responseDTO);
       });
       return list;

    }

    @Override
    public AuthResponseDTO RegisterEmployee(EmployeeRequestDTO employeeRequestDTO) {
        try{
            PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
            Optional<Department> departmentOptional=
                    departmentRepository.findById(employeeRequestDTO.getDepartment());
            if(departmentOptional.isPresent())
            {
                Department department=departmentOptional.get();
                List<role> list=new ArrayList<>();
                employeeRequestDTO.getRoles().stream().forEach(r->{
                    role roles=new role();
                    roles.setId(r);
                    list.add(roles);

                });
                Employee employee=new Employee(employeeRequestDTO,passwordEncoder.encode(employeeRequestDTO.getPassword()),department,list);
                employee=  employeeRepository.save(employee);
                var jwtToken=jwtService.generateToken(employee);
                AuthResponseDTO authResponseDTO=new AuthResponseDTO();
                authResponseDTO.setToken(jwtToken);
                return  authResponseDTO;

            }
            return  null;

        }
        catch (Exception e)
        {
            System.out.println(e);
            return  null;

        }

    }


}
