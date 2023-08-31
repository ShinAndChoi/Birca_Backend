package com.app.birca.controller;

import com.app.birca.config.Login;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.request.SaveCafeRequest;
import com.app.birca.dto.request.UpdateCafeRequest;
import com.app.birca.dto.response.CafeResponse;
import com.app.birca.dto.response.CafeSearchResponse;
import com.app.birca.service.CafeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @PostMapping("/cafe/register")
    public Long saveCafe(@Login LoginUser loginUser, @ModelAttribute SaveCafeRequest request) throws IOException {
        log.info("cafeName = {} ", request.getCafeName());
        log.info("introduction = {} ", request.getIntroduction());
        log.info("file = {}", request.getFile().getOriginalFilename());
        log.info("area = {}", request.getAddress());
        return cafeService.saveCafe(loginUser, request);
    }

    @PatchMapping("/cafe/{cafeId}/update")
    public void updateCafe(@PathVariable Long cafeId, @ModelAttribute UpdateCafeRequest request) throws IOException {
        log.info("cafeName = {} ", request.getCafeName());
        log.info("introduction = {} ", request.getIntroduction());
        log.info("file = {}", request.getFile().getOriginalFilename());
        cafeService.updateCafe(cafeId, request);
    }

    @GetMapping("/cafe/{cafeId}/details")
    public CafeResponse getCafeDetails(@PathVariable Long cafeId) {
        return cafeService.getCafeDetails(cafeId);
    }

    @GetMapping("/cafes/reserved-on")
    public List<CafeSearchResponse> findBySelectedDate(@RequestParam LocalDate selectedDate) {
        log.info("selectedDate = {} ", selectedDate);
        return cafeService.findBySelectedDate(selectedDate);
    }

}
