package in.kafka.redis.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.kafka.redis.entity.CountryDetails;

@Repository
public interface CountryDetailsRepository extends MongoRepository<CountryDetails, String> {

	public CountryDetails findCountryDetailsByAlpha3Code(String alphaCode3);

}
