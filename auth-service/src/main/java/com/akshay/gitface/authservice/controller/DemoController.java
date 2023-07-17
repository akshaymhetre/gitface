package com.akshay.gitface.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

//    @Autowired
//    private OAuth2AuthorizedClientManager manager;

    @Autowired
    OAuth2AuthorizedClientService clientService;

    @GetMapping
    public String sayHello() {
        return "Hi";
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    @GetMapping("/token")
    public String token() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String accessToken = null;
        if (authentication.getClass()
                .isAssignableFrom(OAuth2AuthenticationToken.class)) {
            OAuth2AuthenticationToken oauthToken =
                    (OAuth2AuthenticationToken) authentication;
            String clientRegistrationId =
                    oauthToken.getAuthorizedClientRegistrationId();

            if (clientRegistrationId.equals("github")) {
                OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                        clientRegistrationId, oauthToken.getName());
                accessToken = client.getAccessToken().getTokenValue();
            }
        }
        return "akshay";
    }

//    public String getAccessToken(){
//        OAuth2AuthorizeRequest req = OAuth2AuthorizeRequest.withClientRegistrationId("github").build();
//        OAuth2AuthorizedClient client = manager.authorize(req);
//        assert client != null;
//        return client.getAccessToken().getTokenValue();
//    }


}
