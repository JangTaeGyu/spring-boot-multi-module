package com.example.multimodule.infrastructure.database;

import com.example.multimodule.core.domain.domain.user.User;
import com.example.multimodule.core.domain.domain.user.UserRepository;
import com.example.multimodule.core.domain.support.error.NotFoundException;
import com.example.multimodule.infrastructure.database.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.multimodule.infrastructure.database.entity.QUserEntity.userEntity;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository userRepository;
    private final JPAQueryFactory query;

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserEntity::toDomain);
    }

    @Transactional
    @Override
    public Long create(User user) {
        UserEntity entity = new UserEntity(user);
        return userRepository.save(entity).getId();
    }

    @Transactional
    @Override
    public void updateLatestAccessedAtByEmail(String email, LocalDateTime latestAccessedAt) {
        query.update(userEntity)
                .set(userEntity.latestAccessedAt, latestAccessedAt)
                .where(
                        userEntity.email.eq(email),
                        userEntity.deletedAt.isNull()
                )
                .execute();
    }

    @Transactional
    @Override
    public void updateById(Long id, User user) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User", "id", id));

        if (user.getPassword() != null) {
            entity.updatePassword(user.getPassword());
        }

        entity.updateName(user.getName());
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User", "id", id))
                .delete();
    }
}
