package com.example.multimodule.infrastructure.database;

import com.example.multimodule.core.domain.domain.tag.Tag;
import com.example.multimodule.core.domain.domain.tag.TagRepository;
import com.example.multimodule.infrastructure.database.entity.TagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {
    private final JpaTagRepository tagRepository;

    @Override
    public Long create(String name) {
        TagEntity entity = new TagEntity(name);
        return tagRepository.save(entity).getId();
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return tagRepository.findByName(name).map(TagEntity::toDomain);
    }
}
