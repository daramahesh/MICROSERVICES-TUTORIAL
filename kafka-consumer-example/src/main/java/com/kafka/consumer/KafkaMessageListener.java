package com.kafka.consumer;

import com.kafka.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {
    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);
    @KafkaListener(topics = "customer-event",groupId = "ce-group")
    public void consumeEvents(Customer customer) {
        log.info("consumer consume the events {} ", customer.toString());
    }
//    @KafkaListener(topics = "javatechie-demo",groupId = "jt-group")
//    public void consumeEvents(Customer customer) {
//        log.info("consumer consume the events {} ", customer.toString());
//    }

   /* @KafkaListener(topics = "kafka-demo-5",groupId = "kd-group")
    public void consume2(String message) {
        log.info("consumer2 consume the message {} ", message);
    }

    @KafkaListener(topics = "kafka-demo-5",groupId = "kd-group")
    public void consume3(String message) {
        log.info("consumer3 consume the message {} ", message);
    }

    @KafkaListener(topics = "kafka-demo-5",groupId = "kd-group")
    public void consume4(String message) {
        log.info("consumer4 consume the message {} ", message);
    }*/


}
