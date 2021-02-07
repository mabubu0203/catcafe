package mabubu0203.com.github.catcafe.api.components.security;

import lombok.RequiredArgsConstructor;
import org.openapitools.model.AuthenticationResult;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        var response = webFilterExchange.getExchange().getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return
                Optional.of(response)
                        .map(this::generateBody)
                        .map(response::writeWith)
                        .orElseThrow(RuntimeException::new);
    }

    private Flux<DataBuffer> generateBody(ServerHttpResponse response) {
        // TODO:とりあえず
        var body = new AuthenticationResult();
        body.setKey("認証");
        return
                new Jackson2JsonEncoder()
                        .encode(
                                Flux.just(body),
                                response.bufferFactory(),
                                ResolvableType.forInstance(body),
                                MediaType.APPLICATION_JSON,
                                null);
    }

}
