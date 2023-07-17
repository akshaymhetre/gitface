package com.akshay.gitface.manageservice.client;


import com.akshay.gitface.manageservice.model.*;

import java.util.Collections;
import java.util.List;

public class GithubClientFallback implements GithubClient {
    @Override
    public List<GHRepository> getRepositories() {
        return Collections.emptyList();
    }

    @Override
    public List<GHBranch> getBranches(String user, String repoName) {
        return Collections.emptyList();
    }

    @Override
    public GHBranch getBranch(String user, String repoName, String branchName) {
        return null;
    }

    @Override
    public GHCreatedBranch createBranch(String user, String repoName, GHCreateBranch payload) {
        return null;
    }

    @Override
    public List<GHContent> getContents(String user, String repoName, String path) {
        return null;
    }

    @Override
    public List<GHContent> getBranchContents(String user, String repoName, String path, String branchName) {
        return null;
    }

    @Override
    public GHContent getFileContents(String user, String repoName, String path) {
        return null;
    }

    @Override
    public GHContent getFileBranchContents(String user, String repoName, String path, String branchName) {
        return null;
    }

    @Override
    public void updateContents(String user, String repoName, String path, GHUpdateContent payload) {

    }

    @Override
    public GHCreatePRResponse createPR(String user, String repoName, GHCreatePR payload) {
        return null;
    }
}
