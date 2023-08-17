package com.app.birca.controller;

import com.app.birca.config.Login;
import com.app.birca.dto.request.CafeSearchRequest;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.request.SaveCafeRequest;
import com.app.birca.dto.request.UpdateCafeRequest;
import com.app.birca.dto.response.CafeResponse;
import com.app.birca.dto.response.CafeSearchResponse;
import com.app.birca.service.CafeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @PostMapping("/cafe/register")
    public Long saveCafe(@Login LoginUser loginUser, @ModelAttribute SaveCafeRequest request) {
        return cafeService.saveCafe(loginUser, request);
    }

    @PatchMapping("/cafe/{cafeId}/update")
    public void updateCafe(@PathVariable Long cafeId, @ModelAttribute UpdateCafeRequest request) {
        cafeService.updateCafe(cafeId, request);
    }

    @GetMapping("/cafes")
    public List<CafeSearchResponse> searchCafe(@RequestParam int page, @RequestBody @Valid CafeSearchRequest request) {
        return cafeService.searchCafe(page, request);
    }

    @GetMapping("/cafe/{cafeId}/details")
    public CafeResponse getCafeDetails(@PathVariable Long cafeId) {
        return cafeService.getCafeDetails(cafeId);
    }

}
