package com.example.multimodule.infrastructure.database;

import com.example.multimodule.core.domain.domain.category.Category;
import com.example.multimodule.core.domain.domain.category.CategoryRepository;
import com.example.multimodule.infrastructure.database.entity.CategoryEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.multimodule.infrastructure.database.entity.QCategoryEntity.categoryEntity;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final JpaCategoryRepository categoryRepository;
    private final JPAQueryFactory query;

    @Override
    public List<Category> findAllSorted() {
        return query.select(Projections.constructor(Category.class,
                        categoryEntity.id,
                        categoryEntity.name,
                        categoryEntity.description,
                        categoryEntity.show,
                        categoryEntity.sort,
                        categoryEntity.createdAt,
                        categoryEntity.updatedAt
                ))
                .from(categoryEntity)
                .orderBy(categoryEntity.sort.asc())
                .fetch();
    }

    private Integer maxSort() {
        Integer maxSort = categoryRepository.getMaxSort();
        return maxSort == null ? 0 : maxSort;
    }

    @Transactional
    @Override
    public Long create(Category category) {
        CategoryEntity entity = new CategoryEntity(category);
        entity.setSort(maxSort() + 1);
        return categoryRepository.save(entity).getId();
    }

    @Transactional
    @Override
    public void updateById(Long id, Category category) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"))
                .update(category);
    }
}
