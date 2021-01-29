package mabubu0203.com.github.catcafe.api.components.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

    @Override
    public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
        return Mono.error(new UnsupportedOperationException("Not supported"));
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        var request = serverWebExchange.getRequest();
        var apiKey = request.getHeaders().getFirst("X-API-KEY");
        if (Objects.isNull(apiKey) || apiKey.isEmpty()) {
            log.info("X-API-KEY: " + apiKey);
            return Mono.empty();
        } else {
            log.info("X-API-KEY: " + apiKey);
//            new UsernamePasswordAuthenticationToken(null,null,AuthorityUtils.createAuthorityList("ROLE_USER"));
            var authentication =
                    new AnonymousAuthenticationToken(
                            "authenticated-user",
                            apiKey,// とりあえず
                            AuthorityUtils.createAuthorityList("ROLE_USER"));
            return Mono.just(new SecurityContextImpl(authentication));
        }
    }

}
