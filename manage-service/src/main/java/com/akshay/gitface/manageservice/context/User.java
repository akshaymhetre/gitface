package com.akshay.gitface.manageservice.context;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    String username;
    String fullName;
    String avatarUrl;
}
