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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<SigninResponseDto> signin(@RequestBody SigninRequestDto dto) {
        log.info("Signin requested for {}", dto.username());
        return ResponseEntity.ok(authService.signin(dto));
    }
}
