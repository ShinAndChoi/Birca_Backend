package com.app.birca.repository;

import com.app.birca.domain.Category;
import com.app.birca.domain.entity.IdolGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IdolGroupRepository extends JpaRepository<IdolGroup, Long> {
    Optional<IdolGroup> findByKoreanName(String koreanName);
    List<IdolGroup> findByCategory(Category category);
}
