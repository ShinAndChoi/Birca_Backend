package com.app.birca.controller;

import com.app.birca.config.Login;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.request.SaveCafeInformRequest;
import com.app.birca.dto.response.IdolGroupResponse;
import com.app.birca.dto.response.IdolResponse;
import com.app.birca.service.OnBoardingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OnBoardingController {

    private final OnBoardingService onBoardingService;

    @GetMapping("/idolGroups")
    public List<IdolGroupResponse> findByAllIdolGroups() {
        return onBoardingService.findAllIdolGroups();
    }

    @GetMapping("/idolGroup/search")
    public IdolGroupResponse findBySearching(@RequestParam String groupName) {
        return onBoardingService.findBySearching(groupName);
    }

    @GetMapping("/idolGroup/search/category")
    public List<IdolGroupResponse> findByCategory(@RequestParam String type) {
        return onBoardingService.findByCategory(type);
    }

    @GetMapping("/idols")
    public List<IdolResponse> getIdols(@RequestParam String groupName) {
        return onBoardingService.getIdols(groupName);
    }

    @PostMapping("/save/cafeInform")
    public void saveCafeInform(@Login LoginUser loginUser, @ModelAttribute SaveCafeInformRequest request) throws IOException {
        log.info("cafeName = {}", request.getCafeName());
        log.info("contactNumber = {}", request.getContactNumber());
        log.info("ownerName = {}", request.getOwnerName());
        log.info("businessLicense file Name = {}", request.getBusinessLicense().getOriginalFilename());
        onBoardingService.saveCafeInform(loginUser, request);
    }

}
