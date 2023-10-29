package com.app.birca.controller;

import com.app.birca.config.Login;
import com.app.birca.dto.request.HomeResponse;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/home")
    public HomeResponse home(@Login LoginUser loginUser) {
        return homeService.home(loginUser);
    }

}
