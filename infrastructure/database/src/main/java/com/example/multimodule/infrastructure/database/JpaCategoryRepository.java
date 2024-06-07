package com.example.multimodule.infrastructure.database;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface JpaCategoryRepository extends Repository<CategoryEntity, Long> {
    @Query(value = "SELECT MAX(sort) FROM CategoryEntity ")
    Integer getMaxSort();

    CategoryEntity save(CategoryEntity category);
}
