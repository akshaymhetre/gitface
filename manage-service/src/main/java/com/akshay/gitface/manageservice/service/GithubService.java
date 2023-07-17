package com.akshay.gitface.manageservice.service;

import com.akshay.gitface.manageservice.client.GithubClient;
import com.akshay.gitface.manageservice.service.mixins.GithubApi;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Service
public class GithubService implements GithubApi {
    @Autowired
    private GithubClient githubClient;
}
