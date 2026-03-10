package com.example.demo_websocket_springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Greeting {
    private String content;

    @Override
    public String toString() {
        return String.format("Greeting{ content: '%s%' }", content);
    }
}
