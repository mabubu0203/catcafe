package mabubu0203.com.github.catcafe.infra.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import mabubu0203.com.github.catcafe.common.config.BaseR2dbcConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EntityScan(
    basePackages = {
        "mabubu0203.com.github.catcafe.infra.source.r2dbc.dto",
    })
@EnableR2dbcRepositories(
    basePackages = {
        "mabubu0203.com.github.catcafe.infra.source.r2dbc",
    })
public class R2dbcConfig extends BaseR2dbcConfig {

  @Override
  public ConnectionFactory connectionFactory() {
    String url = "r2dbcs:mysql://localhost:3306/catcafe?useSSL=false&autoReconnect=true&reWriteBatchedInserts=true";
    ConnectionFactory connectionFactory = ConnectionFactories.get(url);
    return connectionFactory;
  }

}
