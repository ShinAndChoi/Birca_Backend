package com.app.birca.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse {

    private final String nickname;
    private final String email;
    private final String accessToken;
    private final String refreshToken;

    public static LoginResponse toLoginResponse(String nickname, String email, String accessToken, String refreshToken) {
        return LoginResponse.builder()
                .nickname(nickname)
                .email(email)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Builder
    public LoginResponse(String nickname, String email, String accessToken, String refreshToken) {
        this.nickname = nickname;
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
