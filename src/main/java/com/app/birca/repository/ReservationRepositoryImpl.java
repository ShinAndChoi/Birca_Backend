package com.app.birca.repository;

import com.app.birca.domain.entity.QReservation;
import com.app.birca.dto.request.CafeReservationRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean isReserved(Long cafeId, CafeReservationRequest request) {
        QReservation reservation = QReservation.reservation;

        return queryFactory.selectFrom(reservation)
                .where(
                        reservation.cafe.id.eq(cafeId),
                        reservation.startDate.before(request.getEndDate()),
                        reservation.endDate.after(request.getStartDate())
                )
                .fetch().isEmpty();
    }

}
