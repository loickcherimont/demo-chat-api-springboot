package com.example.demo_websocket_springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthFormController {

    @GetMapping("/auth-form")
    public String form(Model model) {
        // model.addAttribute("authForm", new AuthForm());
        return "auth-form";
    }
    
}
