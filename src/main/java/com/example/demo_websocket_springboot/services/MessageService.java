package com.example.demo_websocket_springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.example.demo_websocket_springboot.model.Message;
import com.example.demo_websocket_springboot.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message getSanitizedMessage(Message message) {
        message.setContent(getHtmlEscapedString(message.getContent()));
        return message;
    }

    public List<Message> getAllSanitizedMessages() {
        return messageRepository.findAll().stream().map(message -> getSanitizedMessage(message)).toList();
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    private String getHtmlEscapedString(String content) {
        return HtmlUtils.htmlEscape(content);
    }

}
