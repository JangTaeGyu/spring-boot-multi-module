package com.example.multimodule.core.domain.domain.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    private Long id;
    private PostCategory category;
    private String title;
    private String body;
    private boolean show;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @Setter
    private List<PostTag> tags;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostCategory {
        private Long id;
        private String name;

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
