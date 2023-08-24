package com.app.birca.service;

import com.app.birca.domain.entity.Cafe;
import com.app.birca.domain.entity.Reservation;
import com.app.birca.domain.entity.User;
import com.app.birca.dto.request.CafeReservationRequest;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.exception.CafeNotFound;
import com.app.birca.exception.DuplicateReservationException;
import com.app.birca.exception.ReservationNotFound;
import com.app.birca.exception.UserNotFound;
import com.app.birca.repository.CafeRepository;
import com.app.birca.repository.ReservationRepository;
import com.app.birca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CafeRepository cafeRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long reserveCafe(LoginUser loginUser, Long cafeId, CafeReservationRequest request) {
        User user = userRepository.findById(loginUser.getId())
                .orElseThrow(UserNotFound::new);

        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(CafeNotFound::new);

        Boolean isReserved = reservationRepository.isReserved(cafeId, request);
        log.info("isReserved = {} ", isReserved);

        if (!isReserved) {
            throw new DuplicateReservationException();
        }

        Reservation reservation = Reservation.builder()
                .idol(request.getIdolName())
                .cafe(cafe)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .user(user)
                .build();

        return reservationRepository.save(reservation).getId();
    }

    @Transactional
    public void cancelCafeReservation(Long reservationId, LoginUser loginUser) {
        Reservation reservation = reservationRepository.findByIdAndUserId(reservationId, loginUser.getId())
                .orElseThrow(ReservationNotFound::new);
        reservationRepository.delete(reservation);
    }
}
