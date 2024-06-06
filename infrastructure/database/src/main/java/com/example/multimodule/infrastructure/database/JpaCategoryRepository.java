package com.example.multimodule.infrastructure.database;

import org.springframework.data.repository.Repository;

public interface JpaCategoryRepository extends Repository<CategoryEntity, Long> {
}
