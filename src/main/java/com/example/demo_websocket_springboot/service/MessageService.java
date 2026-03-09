package com.example.demo_websocket_springboot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.example.demo_websocket_springboot.model.Greeting;
import com.example.demo_websocket_springboot.model.Message;

@Service
public class MessageService {

    public Greeting getGreetingMessage(Message message) {
        return new Greeting(HtmlUtils.htmlEscape(message.getName()));
    }

}
