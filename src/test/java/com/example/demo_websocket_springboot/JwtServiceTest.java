package com.example.demo_websocket_springboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo_websocket_springboot.model.Role;
import com.example.demo_websocket_springboot.model.User;
import com.example.demo_websocket_springboot.services.JwtService;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService = new JwtService("Oanw8rh6Pmy8crn0GIu19KCjdDH2yWG9kUPkBEh6zfniPl7GXygZDIbHTguOCob9TViN9UElK0KJvqTgsyXF3Q==", 3600000);

    @Test
    void shouldReturnAValidToken() throws Exception {
        
        // GIVEN
        User user = new User();
        user.setEmail("jean.dupont");
        user.setPassword("user789");
        user.setRole(Role.ROLE_USER);
        
        // WHEN
        String token = jwtService.generateToken(user);
        boolean isTokenValid = jwtService.isTokenValid(token, user);

        // ASSERT
        assertThat(isTokenValid).isTrue();
    }

}
