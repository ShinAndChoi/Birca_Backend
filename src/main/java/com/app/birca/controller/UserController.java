package com.app.birca.controller;

import com.app.birca.config.Login;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.response.LoginResponse;
import com.app.birca.dto.response.kakao.GetMemberInfoResponse;
import com.app.birca.service.JwtService;
import com.app.birca.service.KakaoLoginService;
import com.app.birca.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final KakaoLoginService kakaoLoginService;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/token")
    public LoginResponse kakaoLogin(@RequestParam String accessToken) {
        GetMemberInfoResponse userInfo = kakaoLoginService.getKakaoMemberInfo(accessToken);
        Long userId = userService.saveFromKakao(userInfo);

        String generateAccessToken = jwtService.generateAccessToken(userId);
        String generateRefreshToken = jwtService.generateRefreshToken(userId);

        return LoginResponse.toLoginResponse(userInfo.getKakaoAccount().getProfile().getNickname(),
                userInfo.getKakaoAccount().getEmail(), generateAccessToken, generateRefreshToken);
    }

    @PostMapping("/save/favoriteIdol")
    public void saveFavoriteIdol(@Login LoginUser loginUser, @RequestParam String idolName) {
        log.info("idolName = {}", idolName);
        userService.saveFavoriteIdol(loginUser, idolName);
    }

    @PatchMapping("/updateRoleType")
    public void updateRoleType(@Login LoginUser loginUser, @RequestParam String roleType) {
        userService.updateRoleType(loginUser, roleType);
    }

}
