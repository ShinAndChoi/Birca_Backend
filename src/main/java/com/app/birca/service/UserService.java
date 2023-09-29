package com.app.birca.service;

import com.app.birca.domain.entity.Idol;
import com.app.birca.domain.entity.User;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.request.SaveIdolRequest;
import com.app.birca.dto.response.kakao.GetMemberInfoResponse;
import com.app.birca.exception.UserNotFound;
import com.app.birca.repository.IdolRepository;
import com.app.birca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final IdolRepository idolRepository;

    public Long saveFromKakao(GetMemberInfoResponse userInfo) {
        User user = User.builder()
                .nickname(userInfo.getKakaoAccount().getProfile().getNickname())
                .email(userInfo.getKakaoAccount().getEmail())
                .build();

        return saveUser(user);
    }

    public void saveIdolName(Long userId, SaveIdolRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFound::new);

        List<Idol> idols = request.getIdolNames().stream()
                .map(idolName -> new Idol(idolName, user))
                .collect(toList());

        idolRepository.saveAll(idols);
    }

    public void updateRoleType(LoginUser loginUser, String roleType) {
        User user = userRepository.findById(loginUser.getId())
                .orElseThrow(UserNotFound::new);
        user.updateRoleType(roleType);
    }

    private Long saveUser(User user) {
        log.info("email = {}", user.getEmail());
        return userRepository.findByEmail(user.getEmail())
                .map(User::getId)
                .orElseGet(() -> userRepository.save(user).getId());
    }

}
