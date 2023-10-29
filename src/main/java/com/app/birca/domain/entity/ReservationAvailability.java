package com.app.birca.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class ReservationAvailability {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "reservation_availability_id")
    private Long id;

    private LocalDate availabilityPeriod;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    public ReservationAvailability(LocalDate availabilityPeriod, Cafe cafe) {
        this.availabilityPeriod = availabilityPeriod;
        this.cafe = cafe;
        cafe.getReservationAvailabilities().add(this);
    }

}
