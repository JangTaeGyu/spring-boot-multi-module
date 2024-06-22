package com.example.multimodule.core.domain.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserFinder userFinder;
    private final UserCreator userCreator;
    private final UserUpdater userUpdater;
    private final UserDeleter userDeleter;

    public Page<User> searchUsersBy(UserSearchData searchData, Pageable pageable) {
        return userFinder.searchUsersBy(searchData, pageable);
    }

    public Long createUser(UserCreateData data) {
        return userCreator.createUser(data);
    }

    public void updateUser(Long userId, UserUpdateData data) {
        userUpdater.updateUser(userId, data);
    }

    public void deleteUser(Long userId) {
        userDeleter.deleteUser(userId);
    }
}
