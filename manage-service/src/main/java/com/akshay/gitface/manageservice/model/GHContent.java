package com.akshay.gitface.manageservice.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GHContent {
    private String name;
    private String path;
    private String sha;
    private String url;
    private String downloadUrl;
    private String type;
}
