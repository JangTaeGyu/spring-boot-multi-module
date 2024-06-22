package com.example.multimodule.infrastructure.database;

import com.example.multimodule.core.domain.domain.user.User;
import com.example.multimodule.core.domain.domain.user.UserRepository;
import com.example.multimodule.core.domain.domain.user.UserRole;
import com.example.multimodule.core.domain.domain.user.UserSearchData;
import com.example.multimodule.core.domain.support.error.NotFoundException;
import com.example.multimodule.infrastructure.database.entity.UserEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.multimodule.infrastructure.database.entity.QUserEntity.userEntity;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository userRepository;
    private final JPAQueryFactory query;

    private ConstructorExpression<User> selectFields() {
        return Projections.constructor(User.class,
                userEntity.id,
                userEntity.email,
                userEntity.name,
                userEntity.role,
                userEntity.latestAccessedAt,
                userEntity.createdAt,
                userEntity.updatedAt
        );
    }

    private BooleanBuilder toBooleanBuilder(UserSearchData searchData) {
        return new BooleanBuilder(userEntity.deletedAt.isNull())
                .and(containsKeyword(searchData.getKeyword()))
                .and(eqRole(searchData.getRole()));
    }

    @Override
    public Page<User> searchBy(UserSearchData searchData, Pageable pageable) {
        BooleanBuilder booleanBuilder = toBooleanBuilder(searchData);

        List<User> content = query.select(selectFields())
                .from(userEntity)
                .where(booleanBuilder)
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .orderBy(userEntity.createdAt.desc())
                .fetch();

        JPAQuery<Long> count = query.select(userEntity.count())
                .from(userEntity)
                .where(booleanBuilder);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchOne);
    }

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

    public BooleanExpression containsKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) return null;
        return userEntity.email.contains(keyword).or(userEntity.name.contains(keyword));
    }

    public BooleanExpression eqRole(UserRole role) {
        if (role == null) return null;
        return userEntity.role.eq(role);
    }
}
