package com.example.demo_websocket_springboot.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.example.demo_websocket_springboot.model.Greeting;
import com.example.demo_websocket_springboot.model.Message;

class MessageServiceTest {

    private MessageService messageService;

    @Test
    void shouldReturnANewGreetingObjectWithEscapedHtml() throws Exception {

        // GIVEN
        Message message = new Message("<b>John</b>");

        // WHEN
        Greeting result = messageService.getGreetingMessage(message);

        // THEN
        assertThat(result.getContent()).isEqualTo("&lt;b&gt;John&lt;/b&gt;");
    }
}
