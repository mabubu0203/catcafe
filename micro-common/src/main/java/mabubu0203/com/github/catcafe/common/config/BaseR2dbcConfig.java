package mabubu0203.com.github.catcafe.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableR2dbcRepositories(
    basePackages = {
        "mabubu0203.com.github.catcafe.common.source.r2dbc",
    })
@EnableTransactionManagement(proxyTargetClass = true)
public abstract class BaseR2dbcConfig extends AbstractR2dbcConfiguration {

}
