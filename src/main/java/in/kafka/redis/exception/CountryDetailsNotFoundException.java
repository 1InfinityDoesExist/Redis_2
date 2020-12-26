
package in.kafka.redis.exception;

import org.springframework.stereotype.Component;

public class CountryDetailsNotFoundException extends BaseException {

	public CountryDetailsNotFoundException(String message) {
		super(message);
	}

}
