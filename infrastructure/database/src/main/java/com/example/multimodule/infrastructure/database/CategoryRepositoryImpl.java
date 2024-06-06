package com.example.multimodule.infrastructure.database;

import com.example.multimodule.core.domain.domain.category.Category;
import com.example.multimodule.core.domain.domain.category.CategoryRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.multimodule.infrastructure.database.QCategoryEntity.categoryEntity;

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
}
