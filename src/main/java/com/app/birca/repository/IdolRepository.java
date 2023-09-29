package com.app.birca.repository;

import com.app.birca.domain.entity.Idol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdolRepository extends JpaRepository<Idol, Long> {
}
