package com.app.birca.service;

import com.app.birca.domain.entity.Cafe;
import com.app.birca.domain.entity.Reservation;
import com.app.birca.dto.response.MyCafeResponse;
import com.app.birca.dto.response.ReservedCafeResponse;
import com.app.birca.repository.CafeRepository;
import com.app.birca.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

    private final CafeRepository cafeRepository;
    private final ReservationRepository reservationRepository;

    //내가 대관 한 카페 리스트
    public List<ReservedCafeResponse> getMyReservedCafes(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        return reservations.stream().map(r -> ReservedCafeResponse.builder()
                        .cafeId(r.getCafe().getId())
                        .cafeName(r.getCafe().getCafeName())
                        .introduction(r.getCafe().getIntroduction())
                        .build())
                .collect(toList());
    }

    //내가 등록한 카페 리스트
    public List<MyCafeResponse> getMyCafes(Long userId) {
        List<Cafe> cafes = cafeRepository.findByUserId(userId);
        return cafes.stream().map(c -> MyCafeResponse.builder()
                        .cafeName(c.getCafeName())
                        .introduction(c.getIntroduction())
                        .cafeId(c.getId())
                        .imageUrl(c.getCafeImages().stream().map(i -> i.getImageUrl())
                                .collect(toList()))
                        .build())
                .collect(toList());
    }

}
