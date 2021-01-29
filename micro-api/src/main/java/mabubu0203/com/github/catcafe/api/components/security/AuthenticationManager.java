package mabubu0203.com.github.catcafe.api.components.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        Optional.of(authentication)
                .map(Authentication::getPrincipal)
                .map(UserDetails.class::cast)
                .orElseThrow(() ->
                        new BadCredentialsException("The API key was not found or not the expected value."));
        return Mono.just(authentication);
    }

}
