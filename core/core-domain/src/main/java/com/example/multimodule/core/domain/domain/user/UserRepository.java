package com.example.multimodule.core.domain.domain.user;

import java.time.LocalDateTime;

public interface UserRepository {
    void updateLatestAccessedAtByEmail(String email, LocalDateTime latestAccessedAt);
}
