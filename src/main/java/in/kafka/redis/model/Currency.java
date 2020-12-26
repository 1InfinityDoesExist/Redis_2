package in.kafka.redis.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Currency implements Serializable {
	private String code;
	private String name;
	private String symbol;

}
