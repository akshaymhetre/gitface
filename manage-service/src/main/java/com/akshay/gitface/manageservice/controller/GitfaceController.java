package com.akshay.gitface.manageservice.controller;

import com.akshay.gitface.manageservice.client.GHContent;
import com.akshay.gitface.manageservice.client.GHCreatePRResponse;
import com.akshay.gitface.manageservice.dao.CreateBranchRequest;
import com.akshay.gitface.manageservice.dao.GetContentRequest;
import com.akshay.gitface.manageservice.dao.UpdateContentRequest;
import com.akshay.gitface.manageservice.model.CreateContentRequest;
import com.akshay.gitface.manageservice.model.GHBranch;
import com.akshay.gitface.manageservice.model.GHCreatedBranch;
import com.akshay.gitface.manageservice.model.GHRepository;
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
        return githubService.getRepositories();
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


//@RestController
//@RequestMapping("/gitface")
//public class GitfaceController {
//    @Autowired
//    private GithubClient githubClient;
//
//    @GetMapping("/repos")
//    public List<GHRepository> getRepositories() {
//        return githubClient.getRepositories();
//    }
//
//    @GetMapping("/repos/{repoName}/branches")
//    public List<GHBranch> getBranches(@PathVariable("repoName") String repoName) {
//        final String username = UserContext.getCurrentUser().getUsername();
//        return githubClient.getBranches(username, repoName);
//    }
//
//    @GetMapping("/repos/{repoName}/branchesNew")
//    public GHCreatedBranch createBranch(
//            @PathVariable("repoName") String repoName,
//            @RequestParam("fromBranch") String fromBranch,
//            @RequestParam("branchName") String branchName
//    ) {
//        final String username = UserContext.getCurrentUser().getUsername();
//        GHBranch branch = githubClient.getBranch(username, repoName, fromBranch);
//        return githubClient.createBranch(
//                username,
//                repoName,
//                GHCreateBranch.builder()
//                        .ref(branchName)
//                        .sha(branch.getHeadSha())
//                        .build()
//        );
//
//    }
//
//    @GetMapping("/repos/{repoName}/contents/")
//    public List<GHContent> getContentRoot(
//            @PathVariable("repoName") String repoName,
//            @PathVariable(value = "path", required = false) String path,
//            @RequestParam(value = "branchName", defaultValue = "") String branchName
//    ) {
//        final String username = UserContext.getCurrentUser().getUsername();
//        return getContents(repoName, path, branchName, username);
//    }
//
//    @GetMapping("/repos/{repoName}/contents/{path}")
//    public List<GHContent> getContents(
//            @PathVariable("repoName") String repoName,
//            @PathVariable(value = "path", required = false) String path,
//            @RequestParam(value = "branchName", defaultValue = "") String branchName
//    ) {
//        final String username = UserContext.getCurrentUser().getUsername();
//        return getContents(repoName, path, branchName, username);
//    }
//
//    private List<GHContent> getContents(String repoName, String path, String branchName, String username) {
//        if (!branchName.isEmpty()) {
//            // GHBranch branch = githubClient.getBranch(username, repoName, branchName);
//            return githubClient.getBranchContents(username, repoName, path, branchName);
//        } else {
//            return githubClient.getContents(username, repoName, path);
//        }
//    }
//
//    @GetMapping("/repos/{repoName}/contents/{path}/create")
//    public void createContent(
//            @PathVariable("repoName") String repoName,
//            @PathVariable("path") String path,
//            @RequestParam(value = "branchName", defaultValue = "") String branchName,
//            @RequestParam(value = "content", defaultValue = "Readme") String content,
//            @RequestParam(value = "message", defaultValue = "commit message") String commitMessage
//    ) {
//        final String username = UserContext.getCurrentUser().getUsername();
//        final GHUpdateContent.GHUpdateContentBuilder ghUpdateContentBuilder = GHUpdateContent.builder()
//                .content(content)
//                .message(commitMessage);
//
//        if(!branchName.isEmpty()) {
//            ghUpdateContentBuilder.branch(branchName);
//        }
//        final GHUpdateContent updateContent = ghUpdateContentBuilder
//                .build();
//
//        githubClient.updateContents(
//                username,
//                repoName,
//                path,
//                updateContent
//        );
//        System.out.println("Created content");
//    }
//
//    private GHContent getFileContents(String repoName, String path, String branchName, String username) {
//        if (!branchName.isEmpty()) {
//            // GHBranch branch = githubClient.getBranch(username, repoName, branchName);
//            return githubClient.getFileBranchContents(username, repoName, path, branchName);
//        } else {
//            return githubClient.getFileContents(username, repoName, path);
//        }
//    }
//
//    @GetMapping("/repos/{repoName}/contents/{path}/update")
//    public void updateContent(
//            @PathVariable("repoName") String repoName,
//            @PathVariable("path") String path,
//            @RequestParam(value = "branchName", defaultValue = "") String branchName,
//            @RequestParam(value = "content", defaultValue = "Readme") String content,
//            @RequestParam(value = "message", defaultValue = "commit message") String commitMessage
//    ) {
//        final String username = UserContext.getCurrentUser().getUsername();
//        GHContent ghContent = getFileContents(repoName, path, branchName, username);
//        if(ghContent != null) {
//            final GHUpdateContent.GHUpdateContentBuilder ghUpdateContentBuilder = GHUpdateContent.builder()
//                    .sha(ghContent.getSha())
//                    .content(content)
//                    .message(commitMessage);
//
//            if(!branchName.isEmpty()) {
//                ghUpdateContentBuilder.branch(branchName);
//            }
//            final GHUpdateContent updateContent = ghUpdateContentBuilder
//                    .build();
//
//            githubClient.updateContents(
//                    username,
//                    repoName,
//                    path,
//                    updateContent
//            );
//            System.out.println("Updated content");
//        }
//    }
//
//    @GetMapping("/repos/{repoName}/PR")
//    public GHCreatePRResponse createPR(
//            @PathVariable("repoName") String repoName,
//            @RequestParam("base") String baseBranchName,
//            @RequestParam("target") String targetBranchName,
//            @RequestParam(value = "title", defaultValue = "PR Test") String title
//    ) {
//        final String username = UserContext.getCurrentUser().getUsername();
//        return githubClient.createPR(
//                username,
//                repoName,
//                GHCreatePR.builder()
//                        .branch(targetBranchName)
//                        .base(baseBranchName)
//                        .username(username)
//                        .title(title)
//                        .head(username, targetBranchName)
//                        .build()
//        );
//    }
//}
