package com.app.birca.service;

import com.app.birca.domain.entity.FavoriteIdol;
import com.app.birca.domain.entity.User;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.request.SaveFavoriteIdolRequest;
import com.app.birca.dto.response.kakao.GetMemberInfoResponse;
import com.app.birca.exception.UserNotFound;
import com.app.birca.repository.FavoriteIdolRepository;
import com.app.birca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FavoriteIdolRepository favoriteIdolRepository;

    public Long saveFromKakao(GetMemberInfoResponse userInfo) {
        User user = User.builder()
                .nickname(userInfo.getKakaoAccount().getProfile().getNickname())
                .email(userInfo.getKakaoAccount().getEmail())
                .build();

        return saveUser(user);
    }

    public void saveFavoriteIdol(SaveFavoriteIdolRequest request) {
        List<String> idolNames = request.getIdolName();
        idolNames.forEach(idolName -> {
            FavoriteIdol favoriteIdol = new FavoriteIdol(idolName);
            favoriteIdolRepository.save(favoriteIdol);
        });
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
