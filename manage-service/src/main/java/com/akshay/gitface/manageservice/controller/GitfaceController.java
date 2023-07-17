package com.akshay.gitface.manageservice.controller;

import com.akshay.gitface.manageservice.context.UserContext;
import com.akshay.gitface.manageservice.dao.CreateBranchRequest;
import com.akshay.gitface.manageservice.dao.CreateContentRequest;
import com.akshay.gitface.manageservice.dao.GetContentRequest;
import com.akshay.gitface.manageservice.dao.UpdateContentRequest;
import com.akshay.gitface.manageservice.model.*;
import com.akshay.gitface.manageservice.service.GithubService;
import com.akshay.gitface.manageservice.service.mixins.CreatePRRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gitface")
public class GitfaceController {
    @Autowired
    private GithubService githubService;

    @GetMapping("/repos")
    public List<GHRepository> getRepositories() {
        return githubService.getRepositories(UserContext.getCurrentUser().getUsername());
    }

    @GetMapping("/repos/{repoName}/branches")
    public List<GHBranch> getBranches(@PathVariable("repoName") String repoName) {
        return githubService.getBranches(repoName);
    }

    @PostMapping("/repos/{repoName}/branches")
    public GHCreatedBranch createBranch(
            @RequestBody CreateBranchRequest createBranchRequest
    ) {
        return githubService.createBranch(createBranchRequest);
    }

    @GetMapping("/repos/{repoName}/contents/")
    public List<GHContent> getContentRoot(
            GetContentRequest getContentRequest
    ) {
        return githubService.getContents(getContentRequest);
    }

    @GetMapping("/repos/{repoName}/contents/{path}")
    public List<GHContent> getContents(
            @PathVariable(value = "path", required = false) String path,
            GetContentRequest getContentRequest
    ) {
        return githubService.getContents(getContentRequest);
    }

    @PostMapping("/repos/{repoName}/contents/{path}")
    public void createContent(
            @RequestBody CreateContentRequest createContentRequest
    ) {
        githubService.createContent(createContentRequest);
    }


    @PutMapping("/repos/{repoName}/contents/{path}")
    public void updateContent(
            @RequestBody UpdateContentRequest updateContentRequest
    ) {
        githubService.updateContent(updateContentRequest);
    }

    @PostMapping("/repos/{repoName}/PR")
    public GHCreatePRResponse createPR(
            @RequestBody CreatePRRequest createPRRequest
    ) {
        return githubService.createPR(createPRRequest);
    }
}