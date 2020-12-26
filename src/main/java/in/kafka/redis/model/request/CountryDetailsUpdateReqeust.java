package in.kafka.redis.model.request;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.kafka.redis.model.Currency;
import in.kafka.redis.model.Language;
import in.kafka.redis.model.RegionalBloc;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryDetailsUpdateReqeust {

	private String name;
	private List<String> topLevelDomain;
	private String alpha2Code;
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
