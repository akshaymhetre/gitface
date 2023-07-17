package com.akshay.gitface.manageservice.controller;

import com.akshay.gitface.manageservice.client.GithubClient;
import com.akshay.gitface.manageservice.model.GHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private GithubClient githubClient;
    @GetMapping
    public String hello(@RequestHeader Map<String, String> headers) throws IOException {
        final List<GHRepository> publicRepositories = githubClient.getRepositories();
        // System.out.println(publicRepositories.size());
        return "hello";
//        headers.forEach((key, value) -> {
//            System.out.printf("Header '%s' = %s%n", key, value);
//        });
//        // return "hello";
//        final String authorization = headers.get("authorization").replace("Bearer ", "");
//        //final GitHub gitHub = new GitHubBuilder().withAppInstallationToken(authorization).build();
//        final GitHub gitHub = new GitHubBuilder().withOAuthToken(authorization).build();
//        // GitHub github = GitHub.connect();
//        final PagedIterable<GHRepository> ghRepositories = gitHub.listAllPublicRepositories();
//        return "" + ghRepositories.toList().size();
    }
}
