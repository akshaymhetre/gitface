package com.akshay.gitface.manageservice.dao;

import lombok.Data;

public record CreateBranchRequest(String repoName, String fromBranch, String branchName) {
}