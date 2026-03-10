package com.example.demo_websocket_springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.example.demo_websocket_springboot.model.Greeting;
import com.example.demo_websocket_springboot.model.Message;
import com.example.demo_websocket_springboot.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Greeting getGreetingMessage(Message message) {
        return new Greeting(HtmlUtils.htmlEscape(message.getName()));
    }

    public List<Greeting> getAllGreetings() {
        return messageRepository.findAll().stream().map(message -> getGreetingMessage(message)).toList();
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

}
