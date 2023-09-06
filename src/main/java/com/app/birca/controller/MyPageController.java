package com.app.birca.controller;

import com.app.birca.config.Login;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.response.MyCafeResponse;
import com.app.birca.dto.response.ReservedCafeResponse;
import com.app.birca.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/myPage/reservedCafe")
    public List<ReservedCafeResponse> getMyReservedCafes(@Login LoginUser loginUser) {
        return myPageService.getMyReservedCafes(loginUser.getId());
    }

    @GetMapping("/myPage/cafes")
    public List<MyCafeResponse> getMyCafes(@Login LoginUser loginUser) {
        return myPageService.getMyCafes(loginUser.getId());
    }

}
