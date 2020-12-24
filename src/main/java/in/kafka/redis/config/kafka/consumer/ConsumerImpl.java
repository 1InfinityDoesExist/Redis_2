package in.kafka.redis.config.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConsumerImpl {

	public ConsumerImpl() {
		log.info("-----ConsumerImpl constructor-----");
	}

	@KafkaListener(topics = "country", containerFactory = "concurrentKafkaListenerContainerFactory")
	public void consumer(ConsumerRecord<String, String> msg) {
		log.info(":::::Inside consumerImpl class, consumer method():::::");
	}
}
