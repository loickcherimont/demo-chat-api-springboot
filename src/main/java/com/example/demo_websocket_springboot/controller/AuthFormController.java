package com.example.demo_websocket_springboot.controller;

import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.example.demo_websocket_springboot.dto.SigninResponseDto;
import com.example.demo_websocket_springboot.model.AuthForm;
import com.example.demo_websocket_springboot.services.JwtService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthFormController {

    private static final String AUTH_URL = "http://localhost:8080/api/auth/signin";

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @GetMapping("/")
    public String home(HttpSession session) {
        return getValidSessionToken(session).isPresent() ? "forward:/index.html" : "redirect:/auth-form";
    }

    @GetMapping("/auth-form")
    public String form(Model model, HttpSession session) {
        if (getValidSessionToken(session).isPresent()) {
            return "redirect:/";
        }

        model.addAttribute("authForm", new AuthForm());
        return "auth-form";
    }

    @PostMapping("/auth-form")
    public String submit(@ModelAttribute AuthForm authForm, Model model, HttpSession session) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<AuthForm> request = new HttpEntity<>(authForm, headers);

        try {
            SigninResponseDto response = restTemplate.postForObject(AUTH_URL, request, SigninResponseDto.class);

            if (response == null || response.token() == null || response.token().isBlank()) {
                throw new IllegalStateException("JWT token missing from signin response");
            }

            session.setAttribute("JWT_TOKEN", response.token());
            return "redirect:/";
        } catch (Exception ex) {
            log.warn("Authentication failed from form: {}", ex.getMessage());
            model.addAttribute("authForm", authForm);
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            return "auth-form";
        }
    }

    private Optional<String> getValidSessionToken(HttpSession session) {
        Object sessionToken = session.getAttribute("JWT_TOKEN");

        if (!(sessionToken instanceof String token) || token.isBlank()) {
            return Optional.empty();
        }

        String userEmail = jwtService.extractUsername(token);

        if (userEmail == null) {
            session.removeAttribute("JWT_TOKEN");
            return Optional.empty();
        }

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(token, userDetails)) {
                return Optional.of(token);
            }
        } catch (Exception ex) {
            log.warn("Invalid JWT token found in session: {}", ex.getMessage());
        }

        session.removeAttribute("JWT_TOKEN");
        return Optional.empty();
    }
}