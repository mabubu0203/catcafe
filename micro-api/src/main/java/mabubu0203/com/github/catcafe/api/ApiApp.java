package mabubu0203.com.github.catcafe.api;

import mabubu0203.com.github.catcafe.domain.DomainCore;
import mabubu0203.com.github.catcafe.infra.config.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@Import(value = {
        JpaConfig.class,
        DomainCore.class,
})
public class ApiApp {

    public static void main(String[] args) {
        SpringApplication.run(ApiApp.class, args);
    }

}
