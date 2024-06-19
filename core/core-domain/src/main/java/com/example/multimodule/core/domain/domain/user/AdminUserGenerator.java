package com.example.multimodule.core.domain.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminUserGenerator {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void generate(String email, String password, String name) {
        boolean result = userRepository.existsByEmail(email);
        if (!result) {
            User user = User.toDomain(email, passwordEncoder.encode(password), name, UserRole.ADMIN);
            userRepository.create(user);
        }
    }
}
