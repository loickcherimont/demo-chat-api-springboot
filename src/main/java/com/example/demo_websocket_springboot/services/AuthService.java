package com.example.demo_websocket_springboot.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo_websocket_springboot.dto.SigninRequestDto;
import com.example.demo_websocket_springboot.dto.SigninResponseDto;
import com.example.demo_websocket_springboot.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public SigninResponseDto signin(SigninRequestDto dto) {

        User user = userService.findByUsername(dto.username());

        if (user == null) {
            throw new IllegalStateException("Invalid credentials: user not found");
        }

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new IllegalStateException("Invalid credentials: wrong password");
        }

        String token = jwtService.generateToken(user);

        return new SigninResponseDto(
            token,
            user.getEmail(),
            user.getRole()
        );
    }

}
