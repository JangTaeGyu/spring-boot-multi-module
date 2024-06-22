package com.example.multimodule.core.domain.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDeleter {
    private final UserRepository userRepository;

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
