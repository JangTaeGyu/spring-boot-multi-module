package com.example.multimodule.infrastructure.database;

import com.example.multimodule.infrastructure.database.entity.PostEntity;
import org.springframework.data.repository.Repository;

public interface JpaPostRepository extends Repository<PostEntity, Long> {
    PostEntity save(PostEntity entity);
}
