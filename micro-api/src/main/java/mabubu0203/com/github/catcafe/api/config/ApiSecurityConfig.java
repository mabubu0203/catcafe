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
      "/CatCafeApi/actuator/**",
      "/CatCafeApi/swagger/**",
      "/CatCafeApi/v3/**",
      "/CatCafeApi/webjars/**",
      "/CatCafeApi/cats/authentication/x_api_key/generate",
  };

  private static final String[] authorizeUrls = {
      "/CatCafeApi/cats/cast_cat",
      "/CatCafeApi/cats/cast_cats",
      "/CatCafeApi/cats/cast_cats/**",
      "/CatCafeApi/cats/casts",
      "/CatCafeApi/cats/casts/**",
      "/CatCafeApi/cats/contacts",
      "/CatCafeApi/cats/contacts/**",
      "/CatCafeApi/cats/display_menus",
      "/CatCafeApi/cats/display_menus/**",
      "/CatCafeApi/cats/events",
      "/CatCafeApi/cats/events/**",
      "/CatCafeApi/cats/frequently_asked_questions",
      "/CatCafeApi/cats/frequently_asked_questions/**",
      "/CatCafeApi/cats/notices",
      "/CatCafeApi/cats/notices/**",
      "/CatCafeApi/cats/provide_services",
      "/CatCafeApi/cats/provide_services/**",
      "/CatCafeApi/cats/stores",
      "/CatCafeApi/cats/stores/**",
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
