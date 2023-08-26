package com.dataproviderservice.config;


import com.dataproviderservice.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class ApplicationConfig {
    final EmployeeRepository repository;
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByEmailAddress(username);
    }

    //Spring Security provides a variety of options for performing authentication. These options follow a simple contract;
    // an Authentication request is processed by an AuthenticationProvider, and a fully authenticated object with full credentials is returned.
    //
    //The standard and most common implementation is the DaoAuthenticationProvider, which retrieves the user details from a simple,
    // read-only user DAO, the UserDetailsService. This User Details Service only has access to the username in order to retrieve
    // the full user entity, which is enough for most scenarios.
    //More custom scenarios will still need to access the full Authentication request to be able to perform the authentication process.
    // For example, when authenticating against some external, third party service (such as Crowd), both the username and password
    // from the authentication request will be necessary.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    //use for verification that of authentication
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // encode password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
