package com.akshay.gitface.manageservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GHRepository {
    int id;
    String nodeId;
    String name;
    String fullName;
    String description;
    @JsonProperty("private")
    boolean isPrivate;
    String htmlUrl;
}
