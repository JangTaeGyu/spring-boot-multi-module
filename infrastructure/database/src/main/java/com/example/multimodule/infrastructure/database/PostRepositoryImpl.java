package com.example.multimodule.infrastructure.database;

import com.example.multimodule.core.domain.domain.post.Post;
import com.example.multimodule.core.domain.domain.post.PostRepository;
import com.example.multimodule.core.domain.domain.post.PostSearchData;
import com.example.multimodule.core.domain.support.error.NotFoundException;
import com.example.multimodule.infrastructure.database.entity.PostEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.multimodule.infrastructure.database.entity.QCategoryEntity.categoryEntity;
import static com.example.multimodule.infrastructure.database.entity.QPostEntity.postEntity;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final JpaPostRepository postRepository;
    private final JPAQueryFactory query;

    private ConstructorExpression<Post> selectFields() {
        return Projections.constructor(Post.class,
                postEntity.id,
                categoryEntity.id,
                categoryEntity.name,
                postEntity.title,
                postEntity.body,
                postEntity.show,
                postEntity.createdAt,
                postEntity.updatedAt
        );
    }

    private BooleanBuilder toBooleanBuilder(PostSearchData searchData) {
        return new BooleanBuilder(postEntity.deletedAt.isNull())
                .and(eqCategoryId(searchData.getCategoryId()))
                .and(eqShow(searchData.getShow()))
                .and(containsKeyword(searchData.getKeyword()));
    }

    @Override
    public Page<Post> searchBy(PostSearchData searchData, Pageable pageable) {
        BooleanBuilder booleanBuilder = toBooleanBuilder(searchData);

        List<Post> content = query.select(selectFields())
                .from(postEntity)
                .innerJoin(categoryEntity).on(postEntity.categoryId.eq(categoryEntity.id))
                .where(booleanBuilder)
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .orderBy(postEntity.createdAt.desc())
                .fetch();

        JPAQuery<Long> count = query.select(postEntity.count())
                .from(postEntity)
                .where(booleanBuilder);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

    @Transactional
    @Override
    public Long create(Post post) {
        PostEntity entity = new PostEntity(post);
        return postRepository.save(entity).getId();
    }

    @Override
    public Optional<Post> findById(Long postId) {
        return Optional.ofNullable(
                query.select(selectFields())
                        .from(postEntity)
                        .innerJoin(categoryEntity).on(postEntity.categoryId.eq(categoryEntity.id))
                        .where(
                                postEntity.id.eq(postId),
                                postEntity.deletedAt.isNull()
                        )
                        .fetchFirst()
        );
    }

    @Transactional
    @Override
    public void updateById(Long id, Post post) {
        postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post", "id", id))
                .update(post);
    }

    @Transactional
    @Override
    public void setShow(Long id, boolean show) {
        postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post", "id", id))
                .setShow(show);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post", "id", id))
                .delete();
    }

    public BooleanExpression eqCategoryId(Long categoryId) {
        if (categoryId == null) return null;
        return postEntity.categoryId.eq(categoryId);
    }

    public BooleanExpression eqShow(Boolean show) {
        if (show == null) return null;
        return postEntity.show.eq(show);
    }

    public BooleanExpression containsKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) return null;
        return postEntity.title.contains(keyword).or(postEntity.body.contains(keyword));
    }
}
