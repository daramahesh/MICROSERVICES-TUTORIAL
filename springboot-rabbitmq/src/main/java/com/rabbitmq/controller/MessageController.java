package com.rabbitmq.controller;

import com.rabbitmq.dto.Customer;
import com.rabbitmq.producer.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    @Autowired
    private MessagePublisher publisher;
    @PostMapping("/publish")
    public String sendMessage(@RequestParam("message") String message) {
        publisher.sendMessage(message);
        return "message sent successfully";
    }

    @PostMapping("/publish/json")
    public String sendJson(@RequestBody Customer customer) {
        publisher.sendJson(customer);
        return "json sent successfully";
    }
}
