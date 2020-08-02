package mabubu0203.com.github.catcafe.api.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi actuatorApi() {
        String[] paths = {"/actuator/**"};
        return GroupedOpenApi.builder()
                .setGroup("actuator")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi contactApi() {
        String[] paths = {
                "/{cats}/contact",
                "/{cats}/contacts",
                "/{cats}/contacts/{contact_id}"};
        return GroupedOpenApi.builder()
                .setGroup("contact")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi menuApi() {
        String[] paths = {
                "/{cats}/menus",
                "/{cats}/stores/{store_id}/menu",
                "/{cats}/stores/{store_id}/menus/{menu_id}"};
        return GroupedOpenApi.builder()
                .setGroup("menu")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi noticeApi() {
        String[] paths = {
                "/{cats}/notice",
                "/{cats}/notices",
                "/{cats}/notices/{notice_id}"};
        return GroupedOpenApi.builder()
                .setGroup("notice")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi storeApi() {
        String[] paths = {
                "/{cats}/store",
                "/{cats}/stores",
                "/{cats}/stores/{store_id}"};
        return GroupedOpenApi.builder()
                .setGroup("store")
                .pathsToMatch(paths)
                .build();
    }

}
