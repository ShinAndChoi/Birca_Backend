package com.app.birca.controller;

import com.app.birca.dto.response.IdolGroupResponse;
import com.app.birca.dto.response.IdolResponse;
import com.app.birca.service.OnBoardingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OnBoardingController {

    private final OnBoardingService onBoardingService;

    @GetMapping("/idolGroups")
    public List<IdolGroupResponse> findByAllIdolGroups() {
        return onBoardingService.findAllIdolGroups();
    }

    @GetMapping("/idolGroup/search")
    public IdolGroupResponse findBySearching(@RequestParam String idolGroup) {
        return onBoardingService.findBySearching(idolGroup);
    }

    @GetMapping("/idols")
    public List<IdolResponse> getIdols(@RequestParam String groupName) {
        return onBoardingService.getIdols(groupName);
    }

}
