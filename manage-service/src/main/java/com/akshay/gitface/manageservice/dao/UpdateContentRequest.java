package com.akshay.gitface.manageservice.dao;

public record UpdateContentRequest(String repoName, String path, String branchName, String content,
                                   String commitMessage) {
}