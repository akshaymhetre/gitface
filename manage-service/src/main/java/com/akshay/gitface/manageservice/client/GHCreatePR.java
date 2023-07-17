package com.akshay.gitface.manageservice.client;

import com.akshay.gitface.manageservice.service.mixins.CreatePRRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GHCreatePR {
    private String title;
    private String head;
    private String username;
    private String branch;
    private String base;

    public static class GHCreatePRBuilder {
        public GHCreatePR.GHCreatePRBuilder head(String username, String branch) {
            this.username = username;
            this.branch = branch;
            this.head = username+":"+branch;
            return this;
        }

        public GHCreatePR getGhCreatePR(CreatePRRequest createPRRequest, String username) {
            return GHCreatePR.builder()
                    .branch(createPRRequest.targetBranchName())
                    .base(createPRRequest.baseBranchName())
                    .username(username)
                    .title(createPRRequest.title())
                    .head(username, createPRRequest.targetBranchName())
                    .build();
        }
    }
}
