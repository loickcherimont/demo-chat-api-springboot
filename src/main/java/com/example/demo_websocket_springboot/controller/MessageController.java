package com.example.demo_websocket_springboot.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.demo_websocket_springboot.model.Greeting;
import com.example.demo_websocket_springboot.model.Message;
import com.example.demo_websocket_springboot.services.MessageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Greeting greeting(Message message) throws Exception {
        return messageService.getGreetingMessage(message);
    }
}
