package com.akshay.gitface.manageservice.service.mixins;

import com.akshay.gitface.manageservice.model.GHRepository;

import java.util.List;

public interface GithubRepositoryApi extends BaseGithubApi {
    default List<GHRepository> getRepositories() {
        return getGithubClient().getRepositories();
    }
}
