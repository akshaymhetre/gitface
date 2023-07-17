package com.akshay.gitface.manageservice.filter;

import com.akshay.gitface.manageservice.context.TokenContext;
import com.akshay.gitface.manageservice.context.User;
import com.akshay.gitface.manageservice.context.UserContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class RequestAuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String token = getToken(req);
        TokenContext.setCurrentToken(token);

        User user = getUser(req);
        UserContext.setCurrentUser(user);
        try {
            chain.doFilter(request, response);
        } finally {
            TokenContext.setCurrentToken("");
            UserContext.setCurrentUser(null);
        }
    }

    private String getToken(HttpServletRequest req) {
        final String bearerToken = req.getHeader(HttpHeaders.AUTHORIZATION);
        return bearerToken.replace("Bearer ", "");
    }

    private User getUser(HttpServletRequest req) {
        final String userName = req.getHeader("X-USER-NAME");
        final String fullName = req.getHeader("X-USER-FULL-NAME");
        final String avatarUrl = req.getHeader("X-USER-AVATAR");

        return new User(userName, fullName, avatarUrl);
    }
}
