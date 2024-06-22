package com.example.multimodule.infrastructure.database;

import com.example.multimodule.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface JpaUserRepository extends Repository<UserEntity, Long> {
    boolean existsByEmail(String email);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.id = :id AND u.deletedAt IS NULL")
    Optional<UserEntity> findById(Long id);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.email = :email AND u.deletedAt IS NULL")
    Optional<UserEntity> findByEmail(String email);

    UserEntity save(UserEntity entity);
}
