package com.akshay.gitface.manageservice.client;

import com.akshay.gitface.manageservice.context.TokenContext;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class GithubClientConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(HttpHeaders.ACCEPT, "application/vnd.github+json");
            requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + TokenContext.getCurrentToken());
            // requestTemplate.header("User-agent", "OOPDesignSession");
            requestTemplate.header("X-GitHub-Api-Version", "2022-11-28");
        };
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignClientErrorDecoder();
    }

}
