package mabubu0203.com.github.catcafe.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@RequiredArgsConstructor
@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class ApiSecurityConfig {

  private static final String[] excludedAuthorizeUrls = {
      "/CatCafeApi/swagger-ui.html",
      "/actuator/**",
      "/CatCafeApi/swagger/**",
      "/v3/api-docs/**",
      "/CatCafeApi/webjars/**",
      "/cats/authentication/x_api_key/generate",
  };

  private static final String[] authorizeUrls = {
      "/cats/cast_cat",
      "/cats/cast_cats",
      "/cats/cast_cats/**",
      "/cats/casts",
      "/cats/casts/**",
      "/cats/contacts",
      "/cats/contacts/**",
      "/cats/display_menus",
      "/cats/display_menus/**",
      "/cats/events",
      "/cats/events/**",
      "/cats/frequently_asked_questions",
      "/cats/frequently_asked_questions/**",
      "/cats/notices",
      "/cats/notices/**",
      "/cats/provide_services",
      "/cats/provide_services/**",
      "/cats/stores",
      "/cats/stores/**",
  };

  private final ReactiveAuthenticationManager authenticationManager;
  private final ServerAuthenticationConverter authenticationConverter;
  private final ServerAuthenticationFailureHandler authenticationFailureHandler;
  private final ServerSecurityContextRepository securityContextRepository;

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
  public SecurityWebFilterChain springSecurityFilterChain(
      final ServerHttpSecurity serverHttpSecurity) {
    return
        serverHttpSecurity
            .authorizeExchange(exchanges ->
                exchanges
                    .pathMatchers(excludedAuthorizeUrls)
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
    var filter = new AuthenticationWebFilter(this.authenticationManager);

    filter.setSecurityContextRepository(this.securityContextRepository);
    filter.setServerAuthenticationConverter(this.authenticationConverter);
    // 認証を行うパスを設定する
    filter.setRequiresAuthenticationMatcher(
        ServerWebExchangeMatchers.pathMatchers(authorizeUrls)
    );
    filter.setAuthenticationFailureHandler(this.authenticationFailureHandler);
//        filter.setAuthenticationSuccessHandler(null);

    return filter;
  }


}
