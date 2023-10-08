package com.app.birca.repository;

import com.app.birca.domain.entity.Idol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdolRepository extends JpaRepository<Idol, Long> {
    List<Idol> findByIdolGroupId(Long idolGroupId);
}
