package com.example.multimodule.core.domain.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFinder {
    private final UserRepository userRepository;

    public Page<User> searchUsersBy(UserSearchData searchData, Pageable pageable) {
        return userRepository.searchBy(searchData, pageable);
    }
}
