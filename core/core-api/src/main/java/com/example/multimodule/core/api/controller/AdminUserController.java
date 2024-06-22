package com.example.multimodule.core.api.controller;

import com.example.multimodule.core.api.controller.constant.PageConstant;
import com.example.multimodule.core.api.controller.request.UserCreateRequest;
import com.example.multimodule.core.api.controller.request.UserUpdateRequest;
import com.example.multimodule.core.api.controller.response.CreatedResponse;
import com.example.multimodule.core.api.controller.response.PaginatedData;
import com.example.multimodule.core.api.controller.response.PaginationResponse;
import com.example.multimodule.core.domain.domain.user.User;
import com.example.multimodule.core.domain.domain.user.UserRole;
import com.example.multimodule.core.domain.domain.user.UserSearchData;
import com.example.multimodule.core.domain.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> search(
            @RequestParam(defaultValue = "", required = false) String keyword,
            @RequestParam(defaultValue = "", required = false) UserRole role,
            @RequestParam(defaultValue = PageConstant.DEFAULT_PAGE, required = false) int page,
            @RequestParam(defaultValue = PageConstant.DEFAULT_PER_PAGE, required = false) int perPage
    ) {
        UserSearchData request = new UserSearchData(keyword, role);
        Pageable pageable = PageRequest.of(page, perPage);
        Page<User> pageUser = userService.searchUsersBy(request, pageable);
        PaginationResponse<List<User>> response = new PaginationResponse<>(pageUser.getContent(), PaginatedData.of(pageUser));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreatedResponse> create(@RequestBody @Valid UserCreateRequest request) {
        Long userId = userService.createUser(request.toData());
        CreatedResponse response = new CreatedResponse(userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> update(@PathVariable Long userId, @RequestBody @Valid UserUpdateRequest request) {
        userService.updateUser(userId, request.toData());
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
