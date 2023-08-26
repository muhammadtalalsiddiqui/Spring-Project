package com.dataproviderservice.service;

import com.dataproviderservice.DTO.AuthRequestDTO;
import com.dataproviderservice.DTO.AuthResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthService {
    AuthResponseDTO login( AuthRequestDTO authRequestDTO);
    void refreshToken(  HttpServletRequest request,
                                   HttpServletResponse response) throws IOException;
    void logout(  HttpServletRequest request,
                        HttpServletResponse response) throws IOException;
}
