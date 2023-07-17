package com.akshay.gitface.manageservice.context;

public class UserContext {
    private static final ThreadLocal<User> CURRENT_USER = new ThreadLocal<>();

    public static User getCurrentUser() {
        return CURRENT_USER.get();
    }

    public static void setCurrentUser(User user) {
        CURRENT_USER.set(user);
    }
}
