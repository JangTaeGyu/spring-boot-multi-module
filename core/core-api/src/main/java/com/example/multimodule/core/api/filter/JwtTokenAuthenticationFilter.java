package com.example.multimodule.core.api.filter;

import com.example.multimodule.core.api.controller.response.ErrorResponse;
import com.example.multimodule.core.domain.domain.user.JwtTokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenManager jwtTokenManager;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;

    private String parseJwtToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = parseJwtToken(request);
            if (token != null && jwtTokenManager.validate(token)) {
                String email = jwtTokenManager.getClaimTarget(token, "email");
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        } catch (AccessDeniedException e) {
            handleAccessDeniedException(request, response, e);
        } catch (Exception e) {
            handleOtherException(request, response, e);
        }
    }

    private void handleAccessDeniedException(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        ErrorResponse error = new ErrorResponse(HttpStatus.FORBIDDEN.value(), request.getRequestURI(), "Access denied");

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(error.getStatus());
        response.getWriter().println(objectMapper.writeValueAsString(error));
    }

    private void handleOtherException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getRequestURI(), "Server error");

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(error.getStatus());
        response.getWriter().println(objectMapper.writeValueAsString(error));
    }
}
