package com.example.multimodule.infrastructure.database.entity;

import com.example.multimodule.core.domain.domain.tag.Tag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Table(name = "tags")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public TagEntity(String name) {
        this.name = name;
    }

    public Tag toDomain() {
        return new Tag(this.id, this.name);
    }
}
