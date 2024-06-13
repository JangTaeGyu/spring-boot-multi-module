package com.example.multimodule.infrastructure.database;

import com.example.multimodule.infrastructure.database.entity.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface JpaPostRepository extends Repository<PostEntity, Long> {
    PostEntity save(PostEntity entity);

    @Query(value = "SELECT p FROM PostEntity p WHERE p.id = :id AND p.deletedAt IS NULL")
    Optional<PostEntity> findById(Long id);
}
