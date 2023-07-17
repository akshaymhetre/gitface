package com.akshay.gitface.manageservice.service.mixins;

import com.akshay.gitface.manageservice.model.GHContent;
import com.akshay.gitface.manageservice.model.GHUpdateContent;
import com.akshay.gitface.manageservice.dao.GetContentRequest;
import com.akshay.gitface.manageservice.dao.UpdateContentRequest;
import com.akshay.gitface.manageservice.dao.CreateContentRequest;

import java.util.List;

public interface GithubContentApi extends BaseGithubApi {
    default List<GHContent> getContents(GetContentRequest getContentRequest) {
        if (!getContentRequest.branchName().isEmpty()) {
            return getGithubClient().getBranchContents(
                    getUsername(),
                    getContentRequest.repoName(),
                    getContentRequest.path(),
                    getContentRequest.branchName()
            );
        } else {
            return getGithubClient().getContents(
                    getUsername(),
                    getContentRequest.repoName(),
                    getContentRequest.path()
            );
        }
    }


    default void createContent(CreateContentRequest createContentRequest) {
        final GHUpdateContent updateContent =
                GHUpdateContent.builder().getGhUpdateContent(createContentRequest);

        getGithubClient().updateContents(
                getUsername(),
                createContentRequest.repoName(),
                createContentRequest.path(),
                updateContent
        );
        LogHolder.logger().info("Created content");
    }

    default void updateContent(UpdateContentRequest updateContentRequest) {
        final String username = getUsername();
        GHContent ghContent = getFileContents(updateContentRequest);
        if(ghContent != null) {
            final GHUpdateContent updateContent =
                    GHUpdateContent.builder()
                            .getGhUpdateContent(updateContentRequest, ghContent);

            getGithubClient().updateContents(
                    username,
                    updateContentRequest.repoName(),
                    updateContentRequest.path(),
                    updateContent
            );
            LogHolder.logger().info("Updated content");
        }
    }

    private GHContent getFileContents(UpdateContentRequest updateContentRequest) {
        if (!updateContentRequest.branchName().isEmpty()) {
            return getGithubClient().getFileBranchContents(
                    getUsername(),
                    updateContentRequest.repoName(),
                    updateContentRequest.path(),
                    updateContentRequest.branchName()
            );
        } else {
            return getGithubClient().getFileContents(
                    getUsername(),
                    updateContentRequest.repoName(),
                    updateContentRequest.path()
            );
        }
    }
}
