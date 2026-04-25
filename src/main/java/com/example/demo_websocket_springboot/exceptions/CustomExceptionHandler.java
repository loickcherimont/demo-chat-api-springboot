 package com.example.demo_websocket_springboot.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<CustomError> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        CustomError customError = new CustomError();
        customError.setMessage(ex.getMessage());
        customError.setStatus(HttpStatus.UNAUTHORIZED.value());
        customError.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(customError, HttpStatus.UNAUTHORIZED);
    }

}
