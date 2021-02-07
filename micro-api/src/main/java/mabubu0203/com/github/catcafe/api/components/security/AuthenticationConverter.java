package mabubu0203.com.github.catcafe.api.components.security;

import lombok.RequiredArgsConstructor;
import mabubu0203.com.github.catcafe.domain.entity.authentication.XApiKeyEntity;
import mabubu0203.com.github.catcafe.domain.entity.authentication.XApiKeySearchConditions;
import mabubu0203.com.github.catcafe.domain.repository.authentication.AuthenticationRepository;
import mabubu0203.com.github.catcafe.domain.value.XApiKeyToken;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class AuthenticationConverter implements ServerAuthenticationConverter {

    private final AuthenticationRepository authenticationRepository;

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return
                Optional.of(exchange)
                        .map(this::getApiKey)
                        .map(this::getInformation)
                        .map(future -> future.thenApply(this::authorized))
                        .map(Mono::fromCompletionStage)
                        .orElseThrow(RuntimeException::new);//中のメソッドで例外処理が発生する
    }

    private String getApiKey(ServerWebExchange exchange) {
        return
                Optional.of(exchange)
                        .map(ServerWebExchange::getRequest)
                        .map(ServerHttpRequest::getHeaders)
                        .map(httpHeaders -> httpHeaders.getFirst(Headers.X_API_KEY.getKey()))
                        .orElseThrow(() -> new HeaderNotFoundException(""));// RequestHeaderにKeyがないExceptionを返却する
    }

    private CompletableFuture<AuthorizedInformation> getInformation(String apiKey) {
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
                        .map(future ->
                                future.thenApply(stream ->
                                        stream
                                                .findFirst()
                                                .map(this::convertInformation)
                                                .orElse(null))
                        )
                        .orElseThrow(() -> new TokenNotFoundException(""));// RDBにアクセストークンがないExceptionを返却する
    }

    private AuthorizedInformation convertInformation(XApiKeyEntity entity) {
        var authorities = new String[]{"USER"};
        return
                new AuthorizedInformation.AuthorizedInformationBuilder()
                        .accessToken(entity.getToken().value())
                        .expires(entity.getEndDateTime())
                        .authorities(authorities)
                        .build();
    }

    private Authentication authorized(AuthorizedInformation information) {
        return
                Optional.of(information)
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
                        .orElseThrow(RuntimeException::new);// ここでは起こり得ない
    }

}
