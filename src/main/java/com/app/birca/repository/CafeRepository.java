package com.app.birca.repository;

import com.app.birca.domain.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CafeRepository extends JpaRepository<Cafe, Long>, CafeRepositoryCustom {
    List<Cafe> findAll();

    @Query("SELECT DISTINCT c FROM Cafe c JOIN c.reservations r " +
            "WHERE :selectedDate BETWEEN r.startDate AND r.endDate")
    List<Cafe> findBySelectedDate(@Param("selectedDate") LocalDate selectedDate);
}
