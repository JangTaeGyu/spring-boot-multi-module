package com.example.multimodule.core.domain.domain.user;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class UserCreateData {
    private final String email;
    private final String password;
    private final String confirmPassword;
    private final String name;
    private final UserRole role;

    public UserCreateData(String email, String password, String confirmPassword, String name, UserRole role) {
        Assert.notNull(email, "email not null");
        Assert.notNull(password, "password not null");
        Assert.notNull(confirmPassword, "confirmPassword not null");
        Assert.notNull(name, "name not null");
        Assert.notNull(name, "role not null");

        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
        this.role = role;
    }

    public void checkMatchPassword() {
        if (!password.equals(confirmPassword)) {
            throw new NotMatchPasswordException("Passwords do not match");
        }
    }
}
