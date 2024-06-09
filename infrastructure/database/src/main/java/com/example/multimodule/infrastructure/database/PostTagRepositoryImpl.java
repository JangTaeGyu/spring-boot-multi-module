package com.example.multimodule.infrastructure.database;

import com.example.multimodule.core.domain.domain.post.PostTagRepository;
import com.example.multimodule.infrastructure.database.entity.PostTagEntity;
import com.example.multimodule.infrastructure.database.entity.PostTagEntityKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostTagRepositoryImpl implements PostTagRepository {
    private final JpaPostTagRepository postTagRepository;

    @Transactional
    @Override
    public void create(Long postId, Long tagId) {
        PostTagEntityKey entityKey = new PostTagEntityKey(postId, tagId);
        PostTagEntity entity = new PostTagEntity(entityKey);
        postTagRepository.save(entity);
    }
}
