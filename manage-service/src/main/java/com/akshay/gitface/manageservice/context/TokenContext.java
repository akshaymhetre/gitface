package com.akshay.gitface.manageservice.context;

public class TokenContext {
    private static final ThreadLocal<String> CURRENT_TOKEN = new ThreadLocal<>();

    public static String getCurrentToken() {
        return CURRENT_TOKEN.get();
    }

    public static void setCurrentToken(String token) {
        CURRENT_TOKEN.set(token);
    }
}
