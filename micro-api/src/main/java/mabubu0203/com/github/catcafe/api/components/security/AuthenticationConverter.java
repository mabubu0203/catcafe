package mabubu0203.com.github.catcafe.api.components.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationConverter implements ServerAuthenticationConverter {

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
        // ApiKeyによる認証をおこなう
        if (apiKey.isEmpty()) {
            return null;// 認証できない & 不正なリクエストヘッダがきた
        } else if ("aaa".equals(apiKey)) {
            String[] authorities = new String[]{"USER"};
            var information = new AuthorizedInformation.AuthorizedInformationBuilder()
                    .accessToken("")
                    .expires(LocalDate.now())
                    .authorities(authorities)
                    .build();
            return information;
        } else if ("bbb".equals(apiKey)) {
            String[] authorities = new String[]{"USER"};
            var information = new AuthorizedInformation.AuthorizedInformationBuilder()
                    .accessToken("")
                    .expires(LocalDate.now())
                    .authorities(authorities)
                    .build();
            return information;
        } else {
            String[] authorities = new String[]{"USER"};
            var information = new AuthorizedInformation.AuthorizedInformationBuilder()
                    .accessToken("")
                    .expires(LocalDate.now())
                    .authorities(authorities)
                    .build();
            return information;
        }
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
