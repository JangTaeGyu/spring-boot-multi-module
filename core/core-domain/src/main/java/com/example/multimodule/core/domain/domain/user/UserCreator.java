package com.example.multimodule.core.domain.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreator {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long createUser(UserCreateData data) {
        boolean result = userRepository.existsByEmail(data.getEmail());
        if (result) {
            throw new DuplicateEmailException("Email already exists");
        }

        data.checkMatchPassword();
        User user = User.toDomain(data.getEmail(), passwordEncoder.encode(data.getPassword()), data.getName(), data.getRole());
        return userRepository.create(user);
    }
}
