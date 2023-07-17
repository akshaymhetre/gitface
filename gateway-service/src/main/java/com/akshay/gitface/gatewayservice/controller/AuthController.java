package com.akshay.gitface.gatewayservice.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class AuthController {
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Map.of(
                "name", Optional.ofNullable(principal.getAttribute("name")).orElse(""),
                "avatarUrl", Optional.ofNullable(principal.getAttribute("avatar_url")).orElse("avatar_url")
        );
    }
    @GetMapping("/error")
    public String error(@AuthenticationPrincipal OAuth2User principal) {
        if(principal == null)
            return "Please authenticate again.";
        else
            return "";
    }
}
