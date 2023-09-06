package com.app.birca.repository;

import com.app.birca.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationCustom {
    Optional<Reservation> findByIdAndUserId(Long reservationId, Long userId);
    List<Reservation> findByUserId(Long userId);
}
