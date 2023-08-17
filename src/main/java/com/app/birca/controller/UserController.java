package com.app.birca.controller;

import com.app.birca.config.Login;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.response.LoginResponse;
import com.app.birca.dto.response.kakao.GetMemberInfoResponse;
import com.app.birca.service.KakaoLoginService;
import com.app.birca.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final KakaoLoginService kakaoLoginService;
    private final UserService userService;

    @PostMapping("/token")
    public LoginResponse kakaoLogin(@RequestParam String accessToken) {
        GetMemberInfoResponse userInfo = kakaoLoginService.getKakaoMemberInfo(accessToken);
        return userService.saveFromKakao(userInfo);
    }

    @PatchMapping("/updateRoleType")
    public void updateRoleType(@Login LoginUser loginUser, @RequestParam String roleType) {
        userService.updateRoleType(loginUser, roleType);
    }

}
