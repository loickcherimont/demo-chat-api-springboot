package com.example.demo_websocket_springboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo_websocket_springboot.configuration.SecurityConfig;
import com.example.demo_websocket_springboot.controller.MessageRestController;
import com.example.demo_websocket_springboot.services.JwtService;
import com.example.demo_websocket_springboot.services.MessageService;

// Include SecurityConfig, required to run correctly tests using @WebMvcTest
@WebMvcTest(value = MessageRestController.class, includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class))
class MessageRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean private JwtService jwtService;
    @MockitoBean private UserDetailsService userDetailsService;
    @MockitoBean private MessageService messageService;
    @MockitoBean private AuthenticationProvider authenticationProvider;

    @Test
    void shouldReturnHttpError401UnauthorizedIfUserIsNotAuthenticated() throws Exception {
        // ASSERT
        this.mockMvc
            .perform(get("/api/messages"))
            .andExpect(status().isUnauthorized());
    }

}
