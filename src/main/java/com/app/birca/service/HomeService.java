package com.app.birca.service;

import com.app.birca.domain.RoleType;
import com.app.birca.domain.entity.User;
import com.app.birca.dto.request.HomeResponse;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.exception.UserNotFound;
import com.app.birca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {

    private final UserRepository userRepository;

    public HomeResponse home(LoginUser loginUser) {
        User user = userRepository.findById(loginUser.getId())
                .orElseThrow(UserNotFound::new);

        List<String> list1 = List.of("사장페이지");
        List<String> list2 = List.of("팬페이지");

        RoleType roleType = user.getRoleType();
        String type = roleType.getType();
        if (type.equals("cafeOwner")) {
            return new HomeResponse(list1);
        }

        return new HomeResponse(list2);
    }

}
