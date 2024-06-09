package com.example.multimodule.core.domain.domain.tag;

import java.util.Optional;

public interface TagRepository {
    Long create(String name);
    Optional<Tag> findByName(String name);
}
