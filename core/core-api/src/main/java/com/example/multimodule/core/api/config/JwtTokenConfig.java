package com.example.multimodule.core.api.config;

import com.example.multimodule.core.domain.domain.user.JwtTokenManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtTokenConfig {
    private final String secret;
    private final Long expirationDate;

    public JwtTokenConfig(
            @Value("${app.jwt-secret}") String secret,
            @Value("${app.jwt-expiration-date}") Long expirationDate
    ) {
        this.secret = secret;
        this.expirationDate = expirationDate;
    }

    @Bean
    public JwtTokenManager jwtTokenManager() {
        return new JwtTokenManager(secret, expirationDate);
    }
}
