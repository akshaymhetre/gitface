package com.akshay.gitface.gatewayservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
public class CustomGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Triggered custom global filter");

        return exchange.getPrincipal()
                .filter(principal -> principal instanceof OAuth2AuthenticationToken)
                .cast(OAuth2AuthenticationToken.class)
                .map(principal -> withUserInfo(exchange, principal))
                .defaultIfEmpty(exchange).flatMap(chain::filter);
    }

    private ServerWebExchange withUserInfo(ServerWebExchange exchange, OAuth2AuthenticationToken principal) {
        final Map<String, Object> attributes = principal.getPrincipal().getAttributes();

        String fullName = attributes.get("name").toString();
        String username = attributes.get("login").toString();
        String avatarUrl = attributes.get("avatar_url").toString();

        return exchange.mutate()
                .request(r ->
                        r.headers(headers -> {
                            headers.set("X-USER-FULL-NAME", fullName);
                            headers.set("X-USER-NAME", username);
                            headers.set("X-USER-AVATAR", avatarUrl);
                        }))
                .build();
    }
}
