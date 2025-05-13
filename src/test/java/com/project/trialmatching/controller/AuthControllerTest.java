package com.project.trialmatching.controller;

import com.project.trialmatching.dto.AuthResponse;
import com.project.trialmatching.dto.LoginRequest;
import com.project.trialmatching.dto.RegisterRequest;
import com.project.trialmatching.model.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testRegisterAndLoginFlow() {
        UUID hospitalId = UUID.fromString("11111111-1111-1111-1111-111111111111");

        RegisterRequest registerRequest = new RegisterRequest(
                "Dr. Smith",
                "drsmith@example.com",
                "password123",
                Role.CLINICIAN,
                hospitalId
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterRequest> registerEntity = new HttpEntity<>(registerRequest, headers);

        ResponseEntity<AuthResponse> registerResponse = restTemplate.postForEntity(
                "/auth/register", registerEntity, AuthResponse.class
        );
        System.out.println("Register response status: " + registerResponse.getStatusCode());
        System.out.println("Register response body: " + registerResponse.getBody());

        assertThat(registerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(registerResponse.getBody()).isNotNull();
        //assertThat(registerResponse.getBody().getToken()).isNotEmpty();

        LoginRequest loginRequest = new LoginRequest("drsmith@example.com", "password123");
        HttpEntity<LoginRequest> loginEntity = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<AuthResponse> loginResponse = restTemplate.postForEntity(
                "/auth/login", loginEntity, AuthResponse.class
        );

        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(loginResponse.getBody()).isNotNull();
        //assertThat(loginResponse.getBody().getToken()).isNotEmpty();
    }
}
