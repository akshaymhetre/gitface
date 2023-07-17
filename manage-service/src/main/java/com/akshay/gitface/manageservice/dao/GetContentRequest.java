package com.akshay.gitface.manageservice.dao;

public record GetContentRequest(String repoName, String path, String branchName) {
}