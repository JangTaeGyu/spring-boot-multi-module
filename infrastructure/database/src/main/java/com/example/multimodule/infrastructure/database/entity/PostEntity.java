package com.example.multimodule.infrastructure.database.entity;

import com.example.multimodule.core.domain.domain.post.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity @Table(name = "posts")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class PostEntity extends BaseEntity {
    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition="TEXT")
    private String body;

    @Setter
    @ColumnDefault("'false'")
    private boolean show;

    protected LocalDateTime deletedAt;

    public PostEntity(Post post) {
        this.categoryId = post.getCategory().getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.show = post.isShow();
    }

    public void update(Post post) {
        this.categoryId = post.getCategory().getId();
        this.title = post.getTitle();
        this.body = post.getBody();
    }
}
