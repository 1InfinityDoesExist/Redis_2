package in.kafka.redis.service;

import java.util.List;

import org.springframework.stereotype.Component;

import in.kafka.redis.entity.CountryDetails;
import in.kafka.redis.model.request.CountryDetailsCreateRequest;
import in.kafka.redis.model.request.CountryDetailsUpdateReqeust;
import in.kafka.redis.model.response.CountryDetailsResponse;

@Component
public interface CountryDetailsService {

	public void saveCountryDetails(CountryDetailsCreateRequest request);

	public CountryDetails getCountryDetails(String alphaCode3) throws Exception;

	public List<CountryDetails> getAllCountry();

	public CountryDetails deleteCountryDetails(String alpha3Code) throws Exception;

	public CountryDetailsResponse updateCountryDetails(CountryDetailsUpdateReqeust updateRequest, String alpha3Code)
			throws Exception;

}
