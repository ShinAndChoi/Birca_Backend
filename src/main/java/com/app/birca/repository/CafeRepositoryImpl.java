package com.app.birca.repository;

import com.app.birca.domain.entity.Cafe;
import com.app.birca.domain.entity.QCafe;
import com.app.birca.domain.entity.QReservation;
import com.app.birca.dto.request.CafeSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CafeRepositoryImpl implements CafeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Cafe> getCafeResults(int page, CafeSearchRequest request) {
        QCafe cafe = QCafe.cafe;
        QReservation reservation = QReservation.reservation;

        BooleanBuilder predicate = new BooleanBuilder();

        //아이돌 검색
        if (request.getIdolName() != null) {
            predicate.and(reservation.idol.eq(request.getIdolName()));
        }

        //날짜 검색
        if (request.getStartDate() != null && request.getEndDate() != null) {
            predicate.and(reservation.startDate.between(request.getStartDate(), request.getEndDate())
                    .or(reservation.endDate.between(request.getStartDate(), request.getEndDate())));
        }

        //장소 검색
        if (!request.getAddress().getCity().isEmpty() &&
                !request.getAddress().getDistrict().isEmpty() &&
                !request.getAddress().getArea().isEmpty()) {
            predicate.and(cafe.address.city.eq(request.getAddress().getCity()))
                    .and(cafe.address.district.eq(request.getAddress().getDistrict()))
                    .and(cafe.address.area.eq(request.getAddress().getArea()));
        }

        return queryFactory
                .selectFrom(cafe)
                .leftJoin(cafe.reservations, reservation)
                .where(predicate)
                .offset(page * 10)
                .limit(10)
                .fetch();
    }

}
