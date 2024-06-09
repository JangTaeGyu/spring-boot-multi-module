package com.example.multimodule.infrastructure.database;

import com.example.multimodule.infrastructure.database.entity.PostTagEntity;
import com.example.multimodule.infrastructure.database.entity.PostTagEntityKey;
import org.springframework.data.repository.Repository;

public interface JpaPostTagRepository extends Repository<PostTagEntity, PostTagEntityKey> {
    void save(PostTagEntity entity);
}
