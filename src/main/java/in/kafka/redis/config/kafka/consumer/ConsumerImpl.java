package in.kafka.redis.config.kafka.consumer;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import in.kafka.redis.entity.CountryDetails;
import in.kafka.redis.repository.CountryDetailsRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@CacheConfig(cacheNames = { "countryDetails" })
public class ConsumerImpl {

	private Cache cache;
	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private CountryDetailsRepository countryDetailsRepository;

	public ConsumerImpl() {
		log.info("-----ConsumerImpl constructor-----");
	}

	@PostConstruct
	public void init() {
		cache = cacheManager.getCache("countryDetails");
		log.info("List Of CacheNames {}", cacheManager.getCacheNames());
	}

	@SuppressWarnings("unchecked")
	@KafkaListener(topics = "country", containerFactory = "concurrentKafkaListenerContainerFactory")
	public void consumer(ConsumerRecord<String, String> msg) {
		log.info(":::::Inside consumerImpl class, consumer method():::::");
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(msg.value());
			String url = (String) jsonObject.get("url");
			HttpResponse<String> response = Unirest.get(url)
					.header("Cookie", "__cfduid=d1d59026877ab76d42817dc30f62567641606718641").asString();
			JSONArray jsonArray = (JSONArray) new JSONParser().parse(response.getBody());
			jsonArray.forEach(jsonObj -> {
				try {
					CountryDetails countryDetails = new ObjectMapper().readValue(jsonObj.toString(),
							CountryDetails.class);
					// log.info(":::::countryDetails {}", countryDetails.getAlpha3Code());
					countryDetailsRepository.save(countryDetails);
					cache.put(countryDetails.getAlpha3Code(), countryDetails);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			});
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}
}
