package com.example.multimodule.infrastructure.database.entity;

import com.example.multimodule.core.domain.domain.category.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryEntity extends BaseEntity {
    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition="TEXT")
    private String description;

    @Setter
    @ColumnDefault("'true'")
    private boolean show;

    @Setter
    private Integer sort;

    public CategoryEntity(Category category) {
        this.name = category.getName();
        this.description = category.getDescription();
        this.show = category.isShow();
    }

    public void update(Category category) {
        this.name = category.getName();
        this.description = category.getDescription();
    }
}
