package com.example.demo_websocket_springboot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_websocket_springboot.model.Greeting;
import com.example.demo_websocket_springboot.services.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageRestController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<Greeting>> getAllGreetings() {
        return ResponseEntity.ok(messageService.getAllGreetings());
    } 
}
