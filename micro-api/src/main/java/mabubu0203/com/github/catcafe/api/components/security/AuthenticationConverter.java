package mabubu0203.com.github.catcafe.api.components.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationConverter implements ServerAuthenticationConverter {

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        var apiKey = this.getApiKey(exchange);
        log.info("X-API-KEY: " + apiKey);
        var authentication = this.authorized(apiKey);
        return Mono.just(authentication);
    }

    private String getApiKey(ServerWebExchange exchange) {
        return
                Optional.of(exchange)
                        .map(ServerWebExchange::getRequest)
                        .map(ServerHttpRequest::getHeaders)
                        .map(httpHeaders -> httpHeaders.getFirst(Headers.X_API_KEY.getKey()))
                        .orElse("");
    }

    private Authentication authorized(String apiKey) {
        // ApiKeyによる認証をおこなう

        if (apiKey.isEmpty()) {
            return null;
        } else {
            return
                    new UsernamePasswordAuthenticationToken(
                            new User(),
                            new User(),
                            AuthorityUtils.createAuthorityList("ROLE_USER"));
        }
    }
}
