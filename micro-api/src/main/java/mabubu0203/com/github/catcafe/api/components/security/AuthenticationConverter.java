package mabubu0203.com.github.catcafe.api.components.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mabubu0203.com.github.catcafe.domain.entity.authentication.XApiKeySearchConditions;
import mabubu0203.com.github.catcafe.domain.repository.authentication.AuthenticationRepository;
import mabubu0203.com.github.catcafe.domain.value.XApiKeyToken;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationConverter implements ServerAuthenticationConverter {

    private final AuthenticationRepository authenticationRepository;

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        var apiKey = this.getApiKey(exchange);
        log.info("X-API-KEY: " + apiKey);
        var information = this.getInformation(apiKey);
        var authentication = this.authorized(information);
        return Mono.just(authentication);
    }

    private String getApiKey(ServerWebExchange exchange) {
        return
                Optional.of(exchange)
                        .map(ServerWebExchange::getRequest)
                        .map(ServerHttpRequest::getHeaders)
                        .map(httpHeaders -> httpHeaders.getFirst(Headers.X_API_KEY.getKey()))
                        .orElse("");// RequestHeaderにKeyがないExceptionを返却する
    }

    private AuthorizedInformation getInformation(String apiKey) {
        return
                Optional.of(apiKey)
                        .map(XApiKeyToken::new)
                        .map(token -> {
                            var searchConditions = new XApiKeySearchConditions();
                            searchConditions.token(token);
                            searchConditions.specified_date_time(LocalDateTime.now());
                            return searchConditions;
                        })
                        .map(this.authenticationRepository::search)
                        .flatMap(future -> future.thenApply(Stream::findFirst).join())
                        .map(entity -> {
                            String[] authorities = new String[]{"USER"};
                            return new AuthorizedInformation.AuthorizedInformationBuilder()
                                    .accessToken(entity.getToken().value())
                                    .expires(entity.getEndDateTime())
                                    .authorities(authorities)
                                    .build();
                        })
                        .orElse(null);// RDBにアクセストークンがないExceptionを返却する
    }

    private Authentication authorized(AuthorizedInformation information) {
        return
                Optional.ofNullable(information)
                        .map(AuthorizedInformation::getAuthorities)
                        .map(authorities ->
                                User
                                        .withUsername("user")
                                        .password("")
                                        .roles(authorities)
                                        .build()
                        )
                        .map(user ->
                                new UsernamePasswordAuthenticationToken(
                                        user.getUsername(),
                                        user.getPassword(),
                                        user.getAuthorities()))
                        .orElseThrow(() -> new BadCredentialsException("The API key was not found or not the expected value."));
    }
}
