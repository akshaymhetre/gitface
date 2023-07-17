package com.akshay.gitface.manageservice.dao;

public record CreateContentRequest(String repoName, String path, String branchName, String content,
                                   String commitMessage) {
}