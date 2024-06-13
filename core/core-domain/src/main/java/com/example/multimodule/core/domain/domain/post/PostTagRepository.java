package com.example.multimodule.core.domain.domain.post;

import java.util.List;

public interface PostTagRepository {
    void create(Long postId, Long tagId);

    List<PostTag> findAllByPostIds(List<Long> postIds);

    void deleteByPostId(Long postId);
}
