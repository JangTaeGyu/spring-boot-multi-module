package com.example.multimodule.core.domain.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class User {
    private final Long id;
    private final String email;

    @JsonIgnore
    @Setter
    private String password;
    private final String name;
    private final UserRole role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime latestAccessedAt;

    public User(Long id, String email, String password, String name, UserRole role, LocalDateTime latestAccessedAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.latestAccessedAt = latestAccessedAt;
    }

    public static User toDomain(String email, String password, String name, UserRole role) {
        return new User(null, email, password, name, role, null);
    }
}
