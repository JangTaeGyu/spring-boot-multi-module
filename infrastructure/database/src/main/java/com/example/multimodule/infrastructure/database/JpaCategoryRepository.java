package com.example.multimodule.infrastructure.database;

import com.example.multimodule.infrastructure.database.entity.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface JpaCategoryRepository extends Repository<CategoryEntity, Long> {
    @Query(value = "SELECT MAX(sort) FROM CategoryEntity ")
    Integer getMaxSort();

    CategoryEntity save(CategoryEntity category);
}
