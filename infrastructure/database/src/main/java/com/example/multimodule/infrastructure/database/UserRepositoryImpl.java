package com.example.multimodule.infrastructure.database;

import com.example.multimodule.core.domain.domain.user.User;
import com.example.multimodule.core.domain.domain.user.UserRepository;
import com.example.multimodule.infrastructure.database.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository userRepository;

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Long create(User user) {
        UserEntity entity = new UserEntity(user);
        return userRepository.save(entity).getId();
    }

    @Override
    public void updateLatestAccessedAtByEmail(String email, LocalDateTime latestAccessedAt) {

    }
}
