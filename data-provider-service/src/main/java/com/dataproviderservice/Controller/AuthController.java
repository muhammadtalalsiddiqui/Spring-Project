package com.dataproviderservice.Controller;

import com.dataproviderservice.DTO.AuthRequestDTO;
import com.dataproviderservice.DTO.AuthResponseDTO;
import com.dataproviderservice.DTO.EmployeeResponseDTO;
import com.dataproviderservice.service.AuthService;
import com.dataproviderservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Component
@RequestMapping("/auth")
public class AuthController{

    @Autowired
    AuthService authService;

    @PostMapping("/token")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO){
        return  ResponseEntity.ok(authService.login(authRequestDTO));
    }

}