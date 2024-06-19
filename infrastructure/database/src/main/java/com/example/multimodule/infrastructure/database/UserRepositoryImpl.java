package com.example.multimodule.infrastructure.database;

import com.example.multimodule.core.domain.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    @Override
    public void updateLatestAccessedAtByEmail(String email, LocalDateTime latestAccessedAt) {

    }
}
