package in.kafka.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class CommonConfig {
	@Value("${rest.template.timeout:10000}")
	private int timeout;

	private ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory httpComponentsClinetHttpReqeustFactory = new HttpComponentsClientHttpRequestFactory();
		httpComponentsClinetHttpReqeustFactory.setConnectionRequestTimeout(timeout);
		httpComponentsClinetHttpReqeustFactory.setReadTimeout(timeout);
		return httpComponentsClinetHttpReqeustFactory;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
