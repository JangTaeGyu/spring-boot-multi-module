package com.example.multimodule.core.domain.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostRepository {
    Page<Post> searchBy(PostSearchData searchData, Pageable pageable);

    Long create(Post post);

    Optional<Post> findById(Long postId);

    void updateById(Long id, Post post);

    void setShow(Long id, boolean show);

    void deleteById(Long id);
}
