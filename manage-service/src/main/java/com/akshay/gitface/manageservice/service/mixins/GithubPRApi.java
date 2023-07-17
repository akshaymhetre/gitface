package com.akshay.gitface.manageservice.service.mixins;

import com.akshay.gitface.manageservice.model.GHCreatePR;
import com.akshay.gitface.manageservice.model.GHCreatePRResponse;

public interface GithubPRApi extends BaseGithubApi {
    default GHCreatePRResponse createPR(CreatePRRequest createPRRequest) {
        return getGithubClient().createPR(
                getUsername(),
                createPRRequest.repoName(),
                GHCreatePR.builder().getGhCreatePR(createPRRequest, getUsername())
        );
    }
}
