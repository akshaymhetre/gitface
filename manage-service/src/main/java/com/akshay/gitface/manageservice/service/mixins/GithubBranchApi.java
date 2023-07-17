package com.akshay.gitface.manageservice.service.mixins;

import com.akshay.gitface.manageservice.client.GHCreateBranch;
import com.akshay.gitface.manageservice.dao.CreateBranchRequest;
import com.akshay.gitface.manageservice.model.GHBranch;
import com.akshay.gitface.manageservice.model.GHCreatedBranch;

import java.util.List;

public interface GithubBranchApi extends BaseGithubApi {
    default List<GHBranch> getBranches(String repoName) {
        return getGithubClient().getBranches(getUsername(), repoName);
    }

    default GHCreatedBranch createBranch(
            CreateBranchRequest createBranchRequest
    ) {
        GHBranch baseBranch = getGithubClient().getBranch(getUsername(), createBranchRequest.repoName(), createBranchRequest.fromBranch());
        return getGithubClient().createBranch(
                getUsername(),
                createBranchRequest.repoName(),
                GHCreateBranch.builder()
                        .ref(createBranchRequest.branchName())
                        .sha(baseBranch.getHeadSha())
                        .build()
        );

    }
}
