package com.dataproviderservice.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AuthResponseDTO {
    private String token;

    public AuthResponseDTO(String token) {
        this.token = token;
    }

    public AuthResponseDTO() {
    }
}
