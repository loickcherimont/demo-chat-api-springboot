package com.example.demo_websocket_springboot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_websocket_springboot.model.Message;
import com.example.demo_websocket_springboot.services.MessageService;

import lombok.RequiredArgsConstructor;

/**
 * REST controller for history of messages
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageRestController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<Message>> getAllSanitizedMessages() {
        return ResponseEntity.ok(messageService.getAllSanitizedMessages());
    } 
}
