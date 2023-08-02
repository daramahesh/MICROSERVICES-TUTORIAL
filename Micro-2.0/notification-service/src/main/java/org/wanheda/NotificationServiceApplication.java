package org.wanheda;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.wanheda.event.OrderPlacedEvent;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@KafkaListener(topics = "orderNotification")
	public void notification(OrderPlacedEvent orderPlacedEvent) {
		log.info("order received with order id : {} ", orderPlacedEvent.getOrderNumber());
	}
}
