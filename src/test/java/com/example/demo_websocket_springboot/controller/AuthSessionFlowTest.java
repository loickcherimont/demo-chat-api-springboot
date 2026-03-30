package com.example.demo_websocket_springboot.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;

import com.example.demo_websocket_springboot.model.Role;
import com.example.demo_websocket_springboot.model.User;
import com.example.demo_websocket_springboot.services.JwtService;

@SpringBootTest
class AuthSessionFlowTest {

    @Autowired
    private AuthController authController;

    @Autowired
    private AuthFormController authFormController;

    @Autowired
    private JwtService jwtService;

    @Test
    void shouldRedirectToAuthFormWhenNoJwtTokenInSession() {
        MockHttpSession session = new MockHttpSession();

        String viewName = authFormController.home(session);

        assertThat(viewName).isEqualTo("redirect:/auth-form");
    }

    @Test
    void shouldExposeSessionTokenWhenJwtTokenIsValid() {
        User user = new User();
        user.setEmail("john.doe");
        user.setPassword("test123");
        user.setRole(Role.ROLE_USER);

        String token = jwtService.generateToken(user);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("JWT_TOKEN", token);

        ResponseEntity<Map<String, String>> response = authController.getSessionToken(session);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsEntry("token", token);
    }
}
