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
                .group("actuator")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi castApi() {
        String[] paths = {
                "/{cats}/cast_cat",
                "/{cats}/cast_cats",
                "/{cats}/cast_cats/{cast_cat_id}",
                "/{cats}/casts",
                "/{cats}/stores/{store_id}/cast",
                "/{cats}/stores/{store_id}/casts/{cast_id}",};
        return GroupedOpenApi.builder()
                .group("cast")
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
                .group("contact")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi displayMenuApi() {
        String[] paths = {
                "/{cats}/display_menus",
                "/{cats}/stores/{store_id}/display_menu",
                "/{cats}/stores/{store_id}/display_menus/{display_menu_id}"};
        return GroupedOpenApi.builder()
                .group("display_menu")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi eventApi() {
        String[] paths = {
                "/{cats}/event",
                "/{cats}/events",
                "/{cats}/events/{event_id}"};
        return GroupedOpenApi.builder()
                .group("event")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi frequentlyAskedQuestionApi() {
        String[] paths = {
                "/{cats}/frequently_asked_question",
                "/{cats}/frequently_asked_questions",
                "/{cats}/frequently_asked_questions/{faq_id}"};
        return GroupedOpenApi.builder()
                .group("frequently_asked_question")
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
                .group("notice")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi provideServiceApi() {
        String[] paths = {
                "/{cats}/provide_services",
                "/{cats}/stores/{store_id}/provide_service",
                "/{cats}/stores/{store_id}/provide_services/{provide_service_id}"};
        return GroupedOpenApi.builder()
                .group("provide_service")
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
                .group("store")
                .pathsToMatch(paths)
                .build();
    }

}
