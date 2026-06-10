package com.Senai.Filmes.Security;

import jdk.jfr.Enabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Enabled
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public SecurityFilterChain securityFilterChain
}
