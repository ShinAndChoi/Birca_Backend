package com.app.birca.repository;

import com.app.birca.dto.request.CafeReservationRequest;

public interface ReservationCustom {
    Boolean isReserved(Long cafeId, CafeReservationRequest request);
}
