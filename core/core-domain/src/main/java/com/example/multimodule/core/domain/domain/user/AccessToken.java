package com.example.multimodule.core.domain.domain.user;

import lombok.Getter;

@Getter
public class AccessToken {
    private final String tokenType = "Bearer";
    private final String accessToken;
    private final Long expireTime;

    public AccessToken(String accessToken, Long expireTime) {
        this.accessToken = accessToken;
        this.expireTime = expireTime;
    }
}
