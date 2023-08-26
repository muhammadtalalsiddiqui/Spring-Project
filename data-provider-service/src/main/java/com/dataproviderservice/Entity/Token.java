package com.dataproviderservice.Entity;


import com.dataproviderservice.Utils.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(unique = true,name = "token")
    public String token;
    @Column(name = "token_type")

    public String tokenType = "BEARER";
    public boolean revoked;
    public boolean expired;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public Employee user;


}
