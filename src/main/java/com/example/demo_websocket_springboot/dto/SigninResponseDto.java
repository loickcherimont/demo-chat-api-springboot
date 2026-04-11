package com.example.demo_websocket_springboot.dto;

import com.example.demo_websocket_springboot.model.Role;

public record SigninResponseDto(
    String token,
    String username,
    Role role
) {}
