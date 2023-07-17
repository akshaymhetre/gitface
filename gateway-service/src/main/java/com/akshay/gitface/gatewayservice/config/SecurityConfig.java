package com.akshay.gitface.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authorizeExchangeSpec ->
                                authorizeExchangeSpec
                                        .pathMatchers("/**").permitAll()
                                        .pathMatchers("/index.html").permitAll()
                                        .pathMatchers("/config/**").permitAll()
                                        .pathMatchers("/actuator/**").permitAll()
                                        .pathMatchers("/eureka/**").permitAll()
                                        .pathMatchers("/oauth2/**").permitAll()
                                        .pathMatchers("/login/**").permitAll()
                                        .pathMatchers("/error/**").permitAll()
                                        .pathMatchers("/openapi/**").permitAll()
                                        .anyExchange().authenticated()
                                        .and()
                                        .exceptionHandling(e -> e
                                                .authenticationEntryPoint(new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED))
                                        )
                                        .oauth2Login()
                                        //.authenticationFailureHandler(new ServerAuthenticationEntryPointFailureHandler())
                                        .and()
                                        .logout(logoutSpec -> {
                                            final RedirectServerLogoutSuccessHandler handler = new RedirectServerLogoutSuccessHandler();
                                            try {
                                                handler.setLogoutSuccessUrl(new URI("/"));
                                            } catch (URISyntaxException e) {
                                                throw new RuntimeException(e);
                                            }
                                            logoutSpec.logoutSuccessHandler(handler);
                                        }));
        return http.build();
    }
}
