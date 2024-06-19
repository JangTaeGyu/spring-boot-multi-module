package com.example.multimodule.infrastructure.database;

import com.example.multimodule.infrastructure.database.entity.UserEntity;
import org.springframework.data.repository.Repository;

public interface JpaUserRepository extends Repository<UserEntity, Long> {
    boolean existsByEmail(String email);

    UserEntity save(UserEntity entity);
}
