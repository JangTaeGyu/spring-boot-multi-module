package com.example.multimodule.infrastructure.database.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode(of = {"postId", "tagId"})
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostTagKey implements Serializable {
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "tag_id")
    private Long tagId;

    public PostTagKey(Long postId, Long tagId) {
        this.postId = postId;
        this.tagId = tagId;
    }
}
