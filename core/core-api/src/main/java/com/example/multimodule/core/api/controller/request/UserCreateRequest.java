package com.example.multimodule.core.api.controller.request;

import com.example.multimodule.core.domain.domain.user.UserCreateData;
import com.example.multimodule.core.domain.domain.user.UserRole;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class UserCreateRequest {
    @NotBlank
    private String email;

    @NotBlank
    @Min(6)
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String name;

    @NotNull
    private UserRole role;

    public UserCreateData toData() {
        return new UserCreateData(email, password, confirmPassword, name, role);
    }
}
