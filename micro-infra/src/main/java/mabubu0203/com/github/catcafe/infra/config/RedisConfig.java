package mabubu0203.com.github.catcafe.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(
    basePackages = {
        "mabubu0203.com.github.catcafe.infra.source.redis",
    })
public class RedisConfig {

  @Value("${spring.redis.host:localhost}")
  private String host;

  @Value("${spring.redis.port:6379}")
  private Integer port;

  @Value("${spring.redis.database:0}")
  private Integer database;

  @Bean
  public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
    var redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName(this.host);
    redisStandaloneConfiguration.setPort(this.port);
    redisStandaloneConfiguration.setDatabase(this.database);
    return new LettuceConnectionFactory(redisStandaloneConfiguration);
  }

}
