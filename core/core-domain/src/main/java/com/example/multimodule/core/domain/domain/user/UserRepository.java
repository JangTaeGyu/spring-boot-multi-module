package com.example.multimodule.core.domain.domain.user;

import java.time.LocalDateTime;

public interface UserRepository {
    boolean existsByEmail(String email);

    Long create(User user);

    void updateLatestAccessedAtByEmail(String email, LocalDateTime latestAccessedAt);
}
