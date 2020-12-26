package in.kafka.redis.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.kafka.redis.model.Currency;
import in.kafka.redis.model.Language;
import in.kafka.redis.model.RegionalBloc;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "country_details")
public class CountryDetails implements Serializable {
	@Id
	private String id;
	private String name;
	private List<String> topLevelDomain;
	private String alpha2Code;
	private String alpha3Code;
	private List<String> callingCodes;
	private String capital;
	private List<String> altSpellings;
	private String region;
	private String subregion;
	private Long population;
	private List<Integer> latlng;
	private String demonym;
	private Long area;
	private Double gini;
	private List<String> timezones;
	private List<String> borders;
	private String nativeName;
	private String numericCode;
	private List<Currency> currencies;
	private List<Language> languages;
	private Map<String, Object> translations;
	private String flag;
	private String cioc;
	private List<RegionalBloc> regionalBlocs;
}
