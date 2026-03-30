package com.example.demo_websocket_springboot.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_websocket_springboot.dto.SigninRequestDto;
import com.example.demo_websocket_springboot.dto.SigninResponseDto;
import com.example.demo_websocket_springboot.services.AuthService;
import com.example.demo_websocket_springboot.services.JwtService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @PostMapping("/signin")
    public ResponseEntity<SigninResponseDto> signin(@RequestBody SigninRequestDto dto) {
        log.info("Signin requested for {}", dto.username());
        return ResponseEntity.ok(authService.signin(dto));
    }

    @GetMapping("/token")
    public ResponseEntity<Map<String, String>> getSessionToken(HttpSession session) {
        Object sessionToken = session.getAttribute("JWT_TOKEN");

        if (!(sessionToken instanceof String token) || token.isBlank() || !isTokenValid(token)) {
            session.removeAttribute("JWT_TOKEN");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Authentication required"));
        }

        return ResponseEntity.ok(Map.of("token", token));
    }

    private boolean isTokenValid(String token) {
        String userEmail = jwtService.extractUsername(token);

        if (userEmail == null) {
            return false;
        }

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            return jwtService.isTokenValid(token, userDetails);
        } catch (Exception ex) {
            log.warn("Unable to validate session JWT: {}", ex.getMessage());
            return false;
        }
    }
}
