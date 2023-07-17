package com.akshay.gitface.manageservice.model;

import lombok.Data;

@Data
public class GHCreatedBranch {
    private String ref;
    private String url;
}