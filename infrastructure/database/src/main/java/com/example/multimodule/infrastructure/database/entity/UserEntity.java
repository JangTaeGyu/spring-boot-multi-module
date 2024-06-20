package com.example.multimodule.infrastructure.database.entity;

import com.example.multimodule.core.domain.domain.user.User;
import com.example.multimodule.core.domain.domain.user.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = "users")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    private LocalDateTime latestAccessedAt;
    private LocalDateTime deletedAt;

    public UserEntity(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.role = user.getRole();
    }

    public User toDomain() {
        return new User(this.id, this.email, this.password, this.name, this.role, this.latestAccessedAt);
    }
}
