package com.app.birca.repository;

import com.app.birca.domain.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeRepository extends JpaRepository<Cafe, Long>, CafeRepositoryCustom {
    List<Cafe> findAll();
}
