package com.example.multimodule.core.domain.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdater {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void updateUser(Long userId, UserUpdateData data) {
        User user = data.toDomain();
        if (data.hasPassword()) {
            data.checkMatchPassword();
            user.setPassword(passwordEncoder.encode(data.getPassword()));
        }

        userRepository.updateById(userId, user);
    }
}
