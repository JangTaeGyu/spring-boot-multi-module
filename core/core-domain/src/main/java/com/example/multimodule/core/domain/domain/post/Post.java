package com.example.multimodule.core.domain.domain.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Post {
    private final Long id;
    private final PostCategory category;
    private final String title;
    private final String body;
    private final boolean show;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    @Setter
    private List<PostTag> tags;

    @Getter
    public static class PostCategory {
        private final Long id;
        private final String name;

        public PostCategory(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public Post(
            Long id,
            Long categoryId,
            String categoryName,
            String title,
            String body,
            boolean show,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.category = new PostCategory(categoryId, categoryName);
        this.title = title;
        this.body = body;
        this.show = show;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Post toDomain(Long categoryId, String title, String body, boolean show) {
        return new Post(null, categoryId, null, title, body, show, null, null);
    }
}
