package com.akshay.gitface.manageservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class GHBranch {
    String name;
    String headSha;

    @JsonProperty("protected")
    String isProtected;

    @JsonProperty("commit")
    private void unpackNameFromNestedObject(Map<String, Object> commit) {
        headSha = commit.get("sha").toString();
    }
}
