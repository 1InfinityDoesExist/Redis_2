package in.kafka.redis.config.redis;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("127.0.0.1", 6379);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setExposeConnection(true);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
		return redisTemplate;
	}

	@Bean
	public RedisCacheManager redisCacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();

	}

}
