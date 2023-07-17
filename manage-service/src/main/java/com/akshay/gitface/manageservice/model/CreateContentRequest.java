package com.akshay.gitface.manageservice.model;

public record CreateContentRequest(String repoName, String path, String branchName, String content,
                                   String commitMessage) {
}