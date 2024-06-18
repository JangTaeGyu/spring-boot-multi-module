package com.example.multimodule.core.domain.domain.user;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class LoginData {
    private final String email;
    private final String password;

    public LoginData(String email, String password) {
        Assert.notNull(email, "email not null");
        Assert.notNull(password, "password not null");

        this.email = email;
        this.password = password;
    }
}
