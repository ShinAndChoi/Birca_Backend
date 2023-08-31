package com.app.birca.service;

import com.app.birca.domain.entity.Cafe;
import com.app.birca.dto.request.CafeSearchRequest;
import com.app.birca.dto.response.CafeSearchResponse;
import com.app.birca.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final CafeRepository cafeRepository;

    //대관된 카페 검색 1. 아이돌 2. 아이돌 + 날짜 3. 아이돌 + 장소 4. 아이돌 + 날짜 + 장소 검색
    public List<CafeSearchResponse> getReservedCafe(int page, CafeSearchRequest request) {
        List<Cafe> cafes = cafeRepository.getCafeResults(page, request);
        return cafes.stream().map(c -> CafeSearchResponse.builder()
                        .cafeName(c.getCafeName())
                        .address(c.getAddress())
                        .imageUrl(c.getImageUrl())
                        .build())
                .collect(toList());
    }

    //대관할 수 있는 카페 검색
    public List<CafeSearchResponse> getAvailableCafe(int page, CafeSearchRequest request) {
        List<Cafe> cafes = cafeRepository.getCafeResults(page, request);
        return cafes.stream().map(c -> CafeSearchResponse.builder()
                        .cafeName(c.getCafeName())
                        .address(c.getAddress())
                        .imageUrl(c.getImageUrl())
                        .build())
                .collect(toList());
    }

}
