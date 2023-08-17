package com.app.birca.service;

import com.app.birca.config.JwtConfig;
import com.app.birca.exception.jwt.RefreshTokenExpiredException;
import com.app.birca.exception.jwt.TokenNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtConfig jwtConfig;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60L * 60L * 6L; // 액세스 토큰의 만료 시간: 6시간
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60L * 60L * 24L * 30L; // 리프레시 토큰의 만료 시간: 30일

    public String generateAccessToken(Long userId) {
        SecretKey key = getSecretKey();
        return getCompact(userId, key, ACCESS_TOKEN_EXPIRE_TIME);
    }

    public String generateRefreshToken(Long userId) {
        SecretKey key = getSecretKey();
        return getCompact(userId, key, REFRESH_TOKEN_EXPIRE_TIME);
    }

    private String getCompact(Long userId, SecretKey key, long expireTime) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .signWith(key)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .compact();
    }

    public Long getSubject(String token) {
        validateToken(token);
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(jwtConfig.getJwtKey())
                .build()
                .parseClaimsJws(token);
        return Long.parseLong(claims.getBody().getSubject());
    }

    public String reissueAccessToken(String refreshToken) {
        Long userId = validateRefreshToken(refreshToken);
        return generateAccessToken(userId);
    }

    private void validateToken(String token) {
        if (token == null || token.equals("")) {
            throw new TokenNotFoundException();
        }
    }

    private Long validateRefreshToken(String refreshToken) {
        try {
            return getSubject(refreshToken);
        } catch (JwtException e) {
            throw new RefreshTokenExpiredException();
        }
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getJwtKey());
    }

}
