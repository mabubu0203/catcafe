package mabubu0203.com.github.catcafe.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(
    basePackages = {
        "mabubu0203.com.github.catcafe.common.source.jpa.entity",
    })
@EnableJpaRepositories(
    basePackages = {
        "mabubu0203.com.github.catcafe.common.source.jpa",
    })
@EnableTransactionManagement(proxyTargetClass = true)
public abstract class BaseJpaConfig {

}
