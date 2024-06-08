package com.example.multimodule.core.domain.domain.category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAllSorted();

    Long create(Category category);

    void updateById(Long id, Category category);
}