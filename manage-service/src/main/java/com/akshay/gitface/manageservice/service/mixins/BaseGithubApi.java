package com.akshay.gitface.manageservice.service.mixins;

import com.akshay.gitface.manageservice.client.GithubClient;
import com.akshay.gitface.manageservice.context.UserContext;

public interface BaseGithubApi {
    GithubClient getGithubClient();
    default String getUsername() {
        return UserContext.getCurrentUser().getUsername();
    }
}
