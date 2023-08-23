package com.app.birca.controller;

import com.app.birca.config.Login;
import com.app.birca.dto.request.CafeReservationRequest;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/{cafeId}/reservation")
    public void reservation(@Login LoginUser loginUser, @PathVariable Long cafeId, @RequestBody CafeReservationRequest request) {
        log.info("idolName = {} ", request.getIdolName());
        log.info("startDate = {}, endDate = {} ", request.getStartDate(), request.getEndDate());
        reservationService.reserveCafe(loginUser, cafeId, request);
    }

    @DeleteMapping("/{reservationId}/cancelReservation")
    public void cancelCafeReservation(@Login LoginUser loginUser, @PathVariable Long reservationId) {
        reservationService.cancelCafeReservation(reservationId, loginUser);
    }

}
