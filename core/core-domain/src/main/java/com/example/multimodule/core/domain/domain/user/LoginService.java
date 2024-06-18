package com.example.multimodule.core.domain.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenManager jwtTokenManager;
    private final UserRepository userRepository;

    private AccessToken makeAccessTokenDto(Authentication authenticate) {
        CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
        String token = jwtTokenManager.create(userDetails.getUsername());

        return new AccessToken(token, jwtTokenManager.expireTime(null));
    }

    public AccessToken login(LoginData request) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            Authentication authenticate = authenticationManager.authenticate(token);

            // 로그인 시간 저장
            userRepository.updateLatestAccessedAtByEmail(request.getEmail(), LocalDateTime.now());

            return makeAccessTokenDto(authenticate);
        } catch (AuthenticationException e) {
            throw new CustomAuthenticationException("Invalid authentication");
        }
    }
}
