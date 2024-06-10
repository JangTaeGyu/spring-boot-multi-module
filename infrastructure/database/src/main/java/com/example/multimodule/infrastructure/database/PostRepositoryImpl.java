package com.example.multimodule.infrastructure.database;

import com.example.multimodule.core.domain.domain.post.Post;
import com.example.multimodule.core.domain.domain.post.PostRepository;
import com.example.multimodule.infrastructure.database.entity.PostEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final JpaPostRepository postRepository;
    private final JPAQueryFactory query;

    @Transactional
    @Override
    public Long create(Post post) {
        PostEntity entity = new PostEntity(post);
        return postRepository.save(entity).getId();
    }




}
