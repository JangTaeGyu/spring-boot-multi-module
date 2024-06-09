package com.example.multimodule.infrastructure.database.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "post_tags")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostTagEntity {
    @EmbeddedId
    private PostTagEntityKey id;

    public PostTagEntity(PostTagEntityKey id) {
        this.id = id;
    }
}
