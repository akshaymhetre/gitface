package com.akshay.gitface.manageservice.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GHCreateBranch {
    private String ref;
    private String sha;

    public static class GHCreateBranchBuilder {
        public GHCreateBranchBuilder ref(String newBranchName) {
            this.ref = "refs/heads/" + newBranchName;
            return this;
        }
    }
}
