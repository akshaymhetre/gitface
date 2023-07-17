package com.akshay.gitface.manageservice.service.mixins;

public record CreatePRRequest(String repoName, String baseBranchName, String targetBranchName, String title) {
}