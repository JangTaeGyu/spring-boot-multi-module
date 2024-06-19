package com.example.multimodule.core.domain.domain.user;

import java.util.Date;

public interface JwtTokenManager {
    Long expireTime(Date currentDate);

    String makeToken(String email);

    boolean validate(String token);

    String getClaimTarget(String token, String target);
}