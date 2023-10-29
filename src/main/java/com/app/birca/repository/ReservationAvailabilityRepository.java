package com.app.birca.repository;

import com.app.birca.domain.entity.ReservationAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationAvailabilityRepository extends JpaRepository<ReservationAvailability, Long> {
}
