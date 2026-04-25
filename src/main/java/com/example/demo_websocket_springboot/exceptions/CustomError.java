package com.example.demo_websocket_springboot.exceptions;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CustomError {
    private String message;
    private int status;
    private LocalDateTime timestamp;
}
