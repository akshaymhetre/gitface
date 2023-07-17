package com.akshay.gitface.manageservice.client;

import com.akshay.gitface.manageservice.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "github",
        url = "https://api.github.com",
        configuration = GithubClientConfiguration.class,
        fallback = GithubClientFallback.class
)
public interface GithubClient {
    @RequestMapping(method = RequestMethod.GET, value = "/user/repos")
    List<GHRepository> getRepositories();

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{user}/{repoName}/branches")
    List<GHBranch> getBranches(@PathVariable("user") String user, @PathVariable("repoName") String repoName);

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{user}/{repoName}/branches/{branchName}")
    GHBranch getBranch(
            @PathVariable("user") String user,
            @PathVariable("repoName") String repoName,
            @PathVariable("branchName") String branchName
    );

    @RequestMapping(method = RequestMethod.POST, value = "/repos/{user}/{repoName}/git/refs")
    GHCreatedBranch createBranch(
            @PathVariable("user") String user,
            @PathVariable("repoName") String repoName,
            @RequestBody GHCreateBranch payload
    );

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{user}/{repoName}/contents/{path}")
    List<GHContent> getContents(
            @PathVariable("user") String user,
            @PathVariable("repoName") String repoName,
            @PathVariable(value = "path", required = false) String path
    );

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{user}/{repoName}/contents/{path}")
    List<GHContent> getBranchContents(
            @PathVariable("user") String user,
            @PathVariable("repoName") String repoName,
            @PathVariable(value = "path", required = false) String path,
            @RequestParam("ref") String branchName
    );

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{user}/{repoName}/contents/{path}")
    GHContent getFileContents(
            @PathVariable("user") String user,
            @PathVariable("repoName") String repoName,
            @PathVariable(value = "path", required = false) String path
    );

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{user}/{repoName}/contents/{path}")
    GHContent getFileBranchContents(
            @PathVariable("user") String user,
            @PathVariable("repoName") String repoName,
            @PathVariable(value = "path", required = false) String path,
            @RequestParam("ref") String branchName
    );

    @RequestMapping(method = RequestMethod.PUT, value = "/repos/{user}/{repoName}/contents/{path}")
    void updateContents(
            @PathVariable("user") String user,
            @PathVariable("repoName") String repoName,
            @PathVariable("path") String path,
            @RequestBody GHUpdateContent payload
    );

    @RequestMapping(method = RequestMethod.POST, value = "/repos/{user}/{repoName}/pulls")
    GHCreatePRResponse createPR(
            @PathVariable("user") String user,
            @PathVariable("repoName") String repoName,
            @RequestBody GHCreatePR payload
    );
}
