package com.dataproviderservice.service.impl;

import com.dataproviderservice.DTO.AuthRequestDTO;
import com.dataproviderservice.DTO.AuthResponseDTO;
import com.dataproviderservice.Entity.Employee;
import com.dataproviderservice.Repository.EmployeeRepository;
import com.dataproviderservice.config.JwtService;
import com.dataproviderservice.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;
    @Override
    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        try{

            var user=employeeRepository.findByEmailAddress(authRequestDTO.getUsername());
            if(Objects.nonNull(user))
            {
                var jwtToken=jwtService.generateToken(user);
                AuthResponseDTO authResponseDTO=new AuthResponseDTO(jwtToken);
                return  authResponseDTO;
            }
            return null;
        }
        catch (Exception e){
              AuthResponseDTO authResponseDTO=new AuthResponseDTO(e.getMessage());
              return authResponseDTO;
        }

    }
}
