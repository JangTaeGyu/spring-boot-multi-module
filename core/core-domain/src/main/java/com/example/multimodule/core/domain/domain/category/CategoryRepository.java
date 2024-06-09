package com.example.multimodule.core.domain.domain.category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAllSorted();

    Long create(Category category);

    boolean existsById(Long categoryId);

    void updateById(Long id, Category category);

    void setShow(Long id, boolean show);

    Long getCountByIds(List<Long> ids);

    void updateCategorySort(List<Long> ids);
}
