package com.example.demo_websocket_springboot.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.demo_websocket_springboot.model.Message;
import com.example.demo_websocket_springboot.services.MessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final MessageService messageService;

    /**
     * 
     * Use Principal to get authenticated username in STOMP environment
     */
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message handleMessage(Principal principal, @Payload Message message) throws Exception {
        /** Complete the message with send date and its owner's name
         *  before save it into DB and broadcast it
         */
        message.setSendAt(LocalDateTime.now());
        message.setSendBy(principal.getName());
        Message newMessage = messageService.saveMessage(message);
        return messageService.getSanitizedMessage(newMessage);
    }
}
