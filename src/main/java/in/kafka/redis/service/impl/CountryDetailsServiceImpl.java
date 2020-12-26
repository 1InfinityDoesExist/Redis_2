package in.kafka.redis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.kafka.redis.config.kafka.producer.ProducerImpl;
import in.kafka.redis.entity.CountryDetails;
import in.kafka.redis.exception.CountryDetailsNotFoundException;
import in.kafka.redis.model.request.CountryDetailsCreateRequest;
import in.kafka.redis.model.request.CountryDetailsUpdateReqeust;
import in.kafka.redis.model.response.CountryDetailsResponse;
import in.kafka.redis.repository.CountryDetailsRepository;
import in.kafka.redis.service.CountryDetailsService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CountryDetailsServiceImpl implements CountryDetailsService {

	@Autowired
	private ProducerImpl producerImpl;

	@Autowired
	private CountryDetailsRepository countryDetailsRepository;

	@Override
	public void saveCountryDetails(CountryDetailsCreateRequest request) {
		log.info(":::::CountryDetailsServiceImpl Class,  saveCountryDetails method:::::");
		try {
			producerImpl.produce("country", new ObjectMapper().writeValueAsString(request));
			log.info("::::request {}", request);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Cacheable(value = "countryDetails", key = "#alphaCode3")
	public CountryDetails getCountryDetails(String alphaCode3) throws Exception {
		log.info("-----Inside CountryDetailsService Class, getCountryDetails method-----");
		CountryDetails countryDetails = countryDetailsRepository.findCountryDetailsByAlpha3Code(alphaCode3);
		if (ObjectUtils.isEmpty(countryDetails)) {
			throw new CountryDetailsNotFoundException(String.format("CountryDetails not found for: %s ", alphaCode3));
		}
		return countryDetails;
	}

	@Override
	public List<CountryDetails> getAllCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountryDetails deleteCountryDetails(String alpha3Code) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CountryDetailsResponse updateCountryDetails(CountryDetailsUpdateReqeust updateRequest, String alpha3Code)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
