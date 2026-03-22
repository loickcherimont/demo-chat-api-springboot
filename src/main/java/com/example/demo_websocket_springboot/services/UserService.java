package com.example.demo_websocket_springboot.services;

import org.springframework.stereotype.Service;

import com.example.demo_websocket_springboot.model.User;
import com.example.demo_websocket_springboot.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByEmail(username).orElse(null);
    }
}
