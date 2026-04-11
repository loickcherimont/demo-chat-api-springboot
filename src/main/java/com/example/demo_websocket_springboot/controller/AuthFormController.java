package com.example.demo_websocket_springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthFormController {

    // @GetMapping("/auth-form")
    // public String form(Model model) {
    // model.addAttribute("authForm", new AuthForm());
    // return "auth-form";
    // }

    @GetMapping("/auth-form")
    public String form() {
        return "auth-form";
    }

}