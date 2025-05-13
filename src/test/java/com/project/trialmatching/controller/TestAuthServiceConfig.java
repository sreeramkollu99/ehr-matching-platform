package com.project.trialmatching.controller;

import com.project.trialmatching.service.AuthService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestAuthServiceConfig {

    @Bean
    public AuthService authService() {
        return Mockito.mock(AuthService.class);
    }
}
