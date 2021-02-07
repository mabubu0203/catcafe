package mabubu0203.com.github.catcafe.infra.config;

import mabubu0203.com.github.catcafe.common.config.BaseJpaConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(
    basePackages = {
        "mabubu0203.com.github.catcafe.infra.source.jpa.entity",
    })
@EnableJpaRepositories(
    basePackages = {
        "mabubu0203.com.github.catcafe.infra.source.jpa",
    })
public class JpaConfig extends BaseJpaConfig {

}
