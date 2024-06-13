package com.example.multimodule.infrastructure.database;

import com.example.multimodule.core.domain.domain.post.PostTag;
import com.example.multimodule.core.domain.domain.post.PostTagRepository;
import com.example.multimodule.infrastructure.database.entity.PostTagEntity;
import com.example.multimodule.infrastructure.database.entity.PostTagEntityKey;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.multimodule.infrastructure.database.entity.QPostTagEntity.postTagEntity;
import static com.example.multimodule.infrastructure.database.entity.QTagEntity.tagEntity;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostTagRepositoryImpl implements PostTagRepository {
    private final JpaPostTagRepository postTagRepository;
    private final JPAQueryFactory query;

    @Transactional
    @Override
    public void create(Long postId, Long tagId) {
        PostTagEntityKey entityKey = new PostTagEntityKey(postId, tagId);
        PostTagEntity entity = new PostTagEntity(entityKey);
        postTagRepository.save(entity);
    }

    @Override
    public List<PostTag> findAllByPostIds(List<Long> postIds) {
        return query.select(Projections.constructor(PostTag.class,
                        tagEntity.id,
                        tagEntity.name,
                        postTagEntity.id.postId
                ))
                .from(postTagEntity)
                .innerJoin(tagEntity).on(postTagEntity.id.tagId.eq(tagEntity.id))
                .where(
                        postTagEntity.id.postId.in(postIds)
                )
                .fetch();
    }

    @Transactional
    @Override
    public void deleteByPostId(Long postId) {
        query.delete(postTagEntity)
                .where(postTagEntity.id.postId.eq(postId))
                .execute();
    }
}
