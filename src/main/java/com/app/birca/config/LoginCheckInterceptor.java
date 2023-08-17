package com.app.birca.config;

import com.app.birca.exception.jwt.Unauthorized;
import com.app.birca.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String accessToken = request.getHeader("Authorization");

        try {
            Long userId = jwtService.getSubject(accessToken);
            request.setAttribute("userId", userId);
        } catch (JwtException e) {
            log.info("미인증 사용자 요청");
            throw new Unauthorized();
        }

        return true;
    }

}
