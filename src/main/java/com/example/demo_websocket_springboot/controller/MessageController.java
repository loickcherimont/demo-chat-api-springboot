package com.example.demo_websocket_springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final MessageService messageService;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Greeting handleMessage(Message message) throws Exception {
        Message newMessage = messageService.saveMessage(message);
        return messageService.getGreetingMessage(newMessage);
    }
}
