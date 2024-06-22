package com.example.multimodule.core.domain.domain.user;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class UserUpdateData {
    private final String password;
    private final String confirmPassword;
    private final String name;

    public UserUpdateData(String password, String confirmPassword, String name) {
        Assert.notNull(name, "name not null");

        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
    }

    public boolean hasPassword() {
        return password != null && password.isBlank() && confirmPassword != null && confirmPassword.isBlank();
    }

    public void checkMatchPassword() {
        if (!password.equals(confirmPassword)) {
            throw new NotMatchPasswordException("Password and confirmPassword do not match");
        }
    }

    public User toDomain() {
        return new User(null, null, null, this.name, null, null);
    }
}
