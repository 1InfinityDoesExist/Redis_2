package in.kafka.redis.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionalBloc implements Serializable {
	private String acronym;
	private String name;
	private List<String> otherAcronyms;
	private List<String> otherNames;
}
