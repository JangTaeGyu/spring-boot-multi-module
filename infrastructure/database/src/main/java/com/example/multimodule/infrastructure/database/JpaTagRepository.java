package com.example.multimodule.infrastructure.database;

import com.example.multimodule.infrastructure.database.entity.TagEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface JpaTagRepository extends Repository<TagEntity, Long> {
    TagEntity save(TagEntity entity);

    Optional<TagEntity> findByName(String name);
}
