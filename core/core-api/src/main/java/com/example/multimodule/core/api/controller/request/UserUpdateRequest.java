package com.example.multimodule.core.api.controller.request;

import com.example.multimodule.core.domain.domain.user.UserUpdateData;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class UserUpdateRequest {
    @Min(6)
    private String password;
    private String confirmPassword;

    @NotBlank
    private String name;

    public UserUpdateData toData() {
        return new UserUpdateData(password, confirmPassword, name);
    }
}
