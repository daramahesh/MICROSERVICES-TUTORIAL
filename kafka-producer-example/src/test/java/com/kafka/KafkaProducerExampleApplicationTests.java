package com.kafka;

import com.kafka.dto.Customer;
import com.kafka.service.KafkaMessagePublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class KafkaProducerExampleApplicationTests {

	@Container
	static KafkaContainer container = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

	@DynamicPropertySource
	public static void initKafkaProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers",container::getBootstrapServers);
	}

	@Autowired
	private KafkaMessagePublisher publisher;
	@Test
	public void sendEventToTopic() {
		publisher.sendEventsToTopic(new Customer(1,"mahesh","mahesh@gmail.com","7901366731"));
		await().pollInterval(Duration.ofSeconds(3))
				.atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
					// assert statement
				});
	}

}
