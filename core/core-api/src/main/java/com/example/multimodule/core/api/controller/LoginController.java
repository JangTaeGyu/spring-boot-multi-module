package com.example.multimodule.core.api.controller;

import com.example.multimodule.core.api.controller.request.LoginRequest;
import com.example.multimodule.core.api.controller.response.SuccessfulResponse;
import com.example.multimodule.core.domain.domain.user.AccessToken;
import com.example.multimodule.core.domain.domain.user.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        AccessToken token = loginService.login(request.toData());
        SuccessfulResponse<AccessToken> response = new SuccessfulResponse<>(token);
        return ResponseEntity.ok(response);
    }
}
