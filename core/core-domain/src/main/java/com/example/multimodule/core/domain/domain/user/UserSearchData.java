package com.example.multimodule.core.domain.domain.user;

import lombok.Getter;

@Getter
public class UserSearchData {
    private final String keyword;
    private final UserRole role;

    public UserSearchData(String keyword, UserRole role) {
        this.keyword = keyword;
        this.role = role;
    }
}
