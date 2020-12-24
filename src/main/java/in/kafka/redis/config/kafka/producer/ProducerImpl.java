package in.kafka.redis.config.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProducerImpl {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public ProducerImpl() {
		log.info("-------ProducerImpl constructor------");
	}

	public ListenableFuture<SendResult<String, String>> produce(String topic, String msg) {
		ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, msg);
		return send;
	}
}
