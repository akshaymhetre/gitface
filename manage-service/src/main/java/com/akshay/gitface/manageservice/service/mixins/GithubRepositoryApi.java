package com.akshay.gitface.manageservice.service.mixins;

import com.akshay.gitface.manageservice.model.GHRepository;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface GithubRepositoryApi extends BaseGithubApi {

    @Cacheable(value = "gitface-repo")
    default List<GHRepository> getRepositories(String ignoredUsername) {
        LogHolder.logger().info("Fetching repos for [{}] by API call to github client", ignoredUsername);
        return getGithubClient().getRepositories();
    }
}
