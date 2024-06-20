package com.example.multimodule.core.domain.domain.user;

import lombok.Getter;

@Getter
public class AccountData {
    private final Long id;
    private final String email;
    private final String name;
    private final UserRole role;

    public AccountData(Long id, String email, String name, UserRole role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }
}
