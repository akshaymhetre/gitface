package com.akshay.gitface.manageservice.model;

import com.akshay.gitface.manageservice.dao.CreateContentRequest;
import com.akshay.gitface.manageservice.dao.UpdateContentRequest;
import lombok.Builder;
import lombok.Data;

import java.util.Base64;

@Data
@Builder
public class GHUpdateContent {
    private String message;
    private String content;
    private String sha;
    private String branch;

    public static class GHUpdateContentBuilder {
        public GHUpdateContent.GHUpdateContentBuilder content(String content) {
            this.content = Base64.getEncoder().encodeToString(content.getBytes());
            return this;
        }

        public GHUpdateContent getGhUpdateContent(CreateContentRequest createContentRequest) {
            final GHUpdateContent.GHUpdateContentBuilder ghUpdateContentBuilder = GHUpdateContent.builder()
                    .content(createContentRequest.content())
                    .message(createContentRequest.commitMessage());

            if(!createContentRequest.branchName().isEmpty()) {
                ghUpdateContentBuilder.branch(createContentRequest.branchName());
            }
            return ghUpdateContentBuilder.build();
        }

        public GHUpdateContent getGhUpdateContent(UpdateContentRequest updateContentRequest, GHContent ghContent) {
            final GHUpdateContent.GHUpdateContentBuilder ghUpdateContentBuilder = GHUpdateContent.builder()
                    .sha(ghContent.getSha())
                    .content(updateContentRequest.content())
                    .message(updateContentRequest.commitMessage());

            if(!updateContentRequest.branchName().isEmpty()) {
                ghUpdateContentBuilder.branch(updateContentRequest.branchName());
            }
            final GHUpdateContent updateContent = ghUpdateContentBuilder
                    .build();
            return updateContent;
        }
    }


}
