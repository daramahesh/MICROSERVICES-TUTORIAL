package com.rabbitmq.producer;

import com.rabbitmq.config.RabbitmqConfig;
import com.rabbitmq.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Slf4j
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;

    public void sendMessage(String message) {
        log.info("message published {} ", message);
        template.convertAndSend(RabbitmqConfig.EXCHANGE,RabbitmqConfig.ROUTING_KEY,message);
    }

    public void sendJson(Customer customer) {
        template.convertAndSend(RabbitmqConfig.EXCHANGE,RabbitmqConfig.JSON_ROUTING_KEY,customer);
        log.info("customer object published: {} " , customer);
    }

}
