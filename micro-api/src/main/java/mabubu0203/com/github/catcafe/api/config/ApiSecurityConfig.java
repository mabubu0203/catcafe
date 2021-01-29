package mabubu0203.com.github.catcafe.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class ApiSecurityConfig {

    private static final String[] excludedAuthPages = {
            "/CatCafeApi/cats/casts",
            "/CatCafeApi/actuator/**",
            "/CatCafeApi/swagger-ui.html",
            "/CatCafeApi/swagger/**",
            "/CatCafeApi/webjars/**",
//            "/CatCafeApi/webjars/swagger-ui/**",
//            "/CatCafeApi/webjars/swagger-ui/index.html",
            "/CatCafeApi/v3/api-docs/**"
    };

    private final ReactiveAuthenticationManager authenticationManager;
    private final ServerAuthenticationConverter serverAuthenticationConverter;
    private final ServerSecurityContextRepository securityContextRepository;

    private final Map<String, SecurityContext> tokenCache = new ConcurrentHashMap<>();
    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        var user = User
                .withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        var admin = User
                .withUsername("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(final ServerHttpSecurity serverHttpSecurity) {
        return
                serverHttpSecurity
                        .authorizeExchange(exchanges ->
                                exchanges
                                        .pathMatchers(excludedAuthPages)
                                        .permitAll()
                                        .anyExchange()
                                        .authenticated()
                        )
                        .csrf().disable()
                        .httpBasic().disable()
                        .formLogin().disable()
                        .logout().disable()
                        .addFilterAt(this.authenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                        .build();
    }

    @Bean
    public AuthenticationWebFilter authenticationWebFilter() {
        AuthenticationWebFilter filter = new AuthenticationWebFilter(this.authenticationManager);

        filter.setSecurityContextRepository(this.securityContextRepository);
        filter.setServerAuthenticationConverter(this.serverAuthenticationConverter);
        // 認証を行うパスを設定する
        filter.setRequiresAuthenticationMatcher(
                ServerWebExchangeMatchers
                        .pathMatchers(
                                "/CatCafeApi/cats/stores")
        );
//        filter.setAuthenticationFailureHandler(null);
//        filter.setAuthenticationSuccessHandler(null);

        return filter;
    }

}
