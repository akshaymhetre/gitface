package com.akshay.gitface.gatewayservice;

import com.akshay.gitface.gatewayservice.config.CustomGlobalFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import reactor.core.publisher.Mono;

import java.security.Principal;

@SpringBootApplication
public class GatewayServiceApplication {
//	@Autowired
//	private TokenRelayGatewayFilterFactory filterFactory;
//
//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		//@formatter:off
//		return builder.routes()
//				.route("resource", r -> r.path("/resource")
//						.filters(f -> f.filter(filterFactory.apply()))
//						.uri("http://localhost:9000"))
//				.build();
//		//@formatter:on
//	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public GlobalFilter customFilter() {
		return new CustomGlobalFilter();
	}
}
