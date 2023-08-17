package com.app.birca.service;

import com.app.birca.domain.entity.User;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.response.LoginResponse;
import com.app.birca.dto.response.kakao.GetMemberInfoResponse;
import com.app.birca.exception.UserNotFound;
import com.app.birca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public LoginResponse saveFromKakao(GetMemberInfoResponse userInfo) {
        User user = User.builder()
                .nickname(userInfo.getKakaoAccount().getProfile().getNickname())
                .email(userInfo.getKakaoAccount().getEmail())
                .build();

        String accessToken = jwtService.generateAccessToken(user.getId());
        String refreshToken = jwtService.generateRefreshToken(user.getId());

        saveUser(user);

        return LoginResponse.toLoginResponse(user.getNickname(), user.getEmail(), accessToken, refreshToken);
    }

    public void updateRoleType(LoginUser loginUser, String roleType) {
        User user = userRepository.findById(loginUser.getId())
                .orElseThrow(UserNotFound::new);
        user.updateRoleType(roleType);
    }

    private void saveUser(User user) {
        log.info("email = {}", user.getEmail());
        userRepository.findByEmail(user.getEmail())
                .orElseGet(() -> userRepository.save(user));
    }

}
