package com.example.demo_websocket_springboot;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.sql.init.mode=never")
class WebSocketSecurityTest {

    @LocalServerPort
    private int port;

    private WebSocketStompClient stompClient;

    @BeforeEach
    void setup() {
        stompClient = new WebSocketStompClient(
                new StandardWebSocketClient());
    }

    @Test
    void shouldRejectConnectionWithoutJwt() {

        assertThrows(ExecutionException.class, () -> {

            stompClient
                    .connectAsync(
                            "ws://localhost:" + port + "/chat-app",
                            new StompSessionHandlerAdapter() {
                            })
                    .get();

        });

    }

}
