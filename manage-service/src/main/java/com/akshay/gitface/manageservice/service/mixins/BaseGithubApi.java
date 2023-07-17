package com.akshay.gitface.manageservice.service.mixins;

import com.akshay.gitface.manageservice.client.GithubClient;
import com.akshay.gitface.manageservice.context.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

public interface BaseGithubApi {

    @Slf4j
    class LogHolder {
        public static Logger logger() {
            return log;
        }
    }
    GithubClient getGithubClient();
    default String getUsername() {
        return UserContext.getCurrentUser().getUsername();
    }
}
