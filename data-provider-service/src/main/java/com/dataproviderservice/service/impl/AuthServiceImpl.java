package com.dataproviderservice.service.impl;

import com.dataproviderservice.DTO.AuthRequestDTO;
import com.dataproviderservice.DTO.AuthResponseDTO;
import com.dataproviderservice.Entity.Employee;
import com.dataproviderservice.Entity.Token;
import com.dataproviderservice.Repository.EmployeeRepository;
import com.dataproviderservice.Repository.TokenRepository;
import com.dataproviderservice.config.JwtService;
import com.dataproviderservice.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final SecurityContextLogoutHandler securityContextLogoutHandler =new SecurityContextLogoutHandler();


    @Override
    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        try{

            var user=employeeRepository.findByEmailAddressOrName(authRequestDTO.getUsername(),authRequestDTO.getUsername());
            if(Objects.nonNull(user))
            {
                BCryptPasswordEncoder encoded = new BCryptPasswordEncoder();

                boolean matches = encoded.matches(authRequestDTO.getPassword(), user.getPassword());
                if(matches)
                {
                    var jwtToken=jwtService.generateToken(user);
                  Claims claims= jwtService.extractAllClaims(jwtToken);

                    revokeAllUserTokens(user);
                    saveUserToken(user, jwtToken);
                    AuthResponseDTO authResponseDTO=new AuthResponseDTO();
                    authResponseDTO.setName(claims.get("Full name").toString());
                    authResponseDTO.setDepartment( claims.get("Contact").toString());
                    authResponseDTO.setContactNumber(claims.get("Department").toString());
                    authResponseDTO.setToken(jwtToken);
                    return  authResponseDTO;
                }

            }
            return null;
        }
        catch (Exception e){
              AuthResponseDTO authResponseDTO=new AuthResponseDTO();
              return authResponseDTO;
        }

    }
    private void saveUserToken(Employee user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType("BEARER")
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Employee user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUserName(refreshToken);
        if (userEmail != null) {
            var user = this.employeeRepository.findByEmailAddress(userEmail);

            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
//                var authResponse = AuthResponseDTO.builder()
//                        .token(accessToken)
//                        .build();
//                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    @Override
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        securityContextLogoutHandler.logout(request,response,authentication);
//        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//            return;
//        }
//        refreshToken = authHeader.substring(7);
//        userEmail = jwtService.extractUserName(refreshToken);
//        if (userEmail != null) {
//            var user = this.employeeRepository.findByEmailAddress(userEmail);
//
//            if (jwtService.isTokenValid(refreshToken, user)) {
//                revokeAllUserTokens(user);
//                SecurityContextHolder.clearContext();
//
//            }
//        }
        return "Logout Sucessfully";
    }
}
