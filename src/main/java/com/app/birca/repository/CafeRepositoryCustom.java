package com.app.birca.repository;

import com.app.birca.domain.entity.Cafe;
import com.app.birca.dto.request.CafeSearchRequest;

import java.util.List;

public interface CafeRepositoryCustom {
    List<Cafe> getCafeResults(int page, CafeSearchRequest request);
}
