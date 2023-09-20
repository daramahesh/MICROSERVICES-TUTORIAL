package com.rabbitmq.consumer;

import com.rabbitmq.config.RabbitmqConfig;
import com.rabbitmq.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageConsumer {

  /*  @RabbitListener(queues = RabbitmqConfig.QUEUE)
    public void ConsumeMessage(String message) {
      log.info("message consumed: {} ", message);
    }*/

    @RabbitListener(queues = RabbitmqConfig.JSON_QUEUE)
    public void ConsumeJson(Customer customer) {
        log.info("customer object consumed: {} ", customer.toString());
    }
}
