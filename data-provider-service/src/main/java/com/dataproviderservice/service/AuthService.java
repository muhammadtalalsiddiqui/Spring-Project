package com.dataproviderservice.service;

import com.dataproviderservice.DTO.AuthRequestDTO;
import com.dataproviderservice.DTO.AuthResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    AuthResponseDTO login( AuthRequestDTO authRequestDTO);
}
