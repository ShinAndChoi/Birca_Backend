package com.app.birca.controller;

import com.app.birca.config.Login;
import com.app.birca.dto.request.CafeReservationRequest;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/{cafeId}/reservation")
    public void reservation(@Login LoginUser loginUser, @PathVariable Long cafeId, @RequestBody CafeReservationRequest request) {
        reservationService.reservation(loginUser, cafeId, request);
    }

}
