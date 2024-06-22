package com.example.multimodule.core.domain.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository {
    Page<User> searchBy(UserSearchData searchData, Pageable pageable);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Long create(User user);

    void updateLatestAccessedAtByEmail(String email, LocalDateTime latestAccessedAt);

    void updateById(Long id, User user);

    void deleteById(Long id);
}
