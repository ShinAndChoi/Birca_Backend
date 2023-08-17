package com.app.birca.service;

import com.app.birca.domain.entity.Cafe;
import com.app.birca.domain.entity.User;
import com.app.birca.dto.request.CafeSearchRequest;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.request.SaveCafeRequest;
import com.app.birca.dto.request.UpdateCafeRequest;
import com.app.birca.dto.response.CafeResponse;
import com.app.birca.dto.response.CafeSearchResponse;
import com.app.birca.exception.CafeNotFound;
import com.app.birca.exception.UserNotFound;
import com.app.birca.repository.CafeRepository;
import com.app.birca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeService {

    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;

    public Long saveCafe(LoginUser loginUser, SaveCafeRequest request) {
        User user = userRepository.findById(loginUser.getId())
                .orElseThrow(UserNotFound::new);

        Cafe cafe = Cafe.builder()
                .cafeName(request.getCafeName())
                .introduction(request.getIntroduction())
                .imageUrl(request.getFile().getOriginalFilename())
                .address(request.getAddress())
                .user(user)
                .build();

        return cafeRepository.save(cafe).getId();
    }

    @Transactional
    public void updateCafe(Long cafeId, UpdateCafeRequest request) {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(CafeNotFound::new);

        User user = cafe.getUser();

        cafe.updateCafe(request.getCafeName(), request.getIntroduction(),
                request.getFile().getOriginalFilename(), user);
    }

    public List<CafeSearchResponse> searchCafe(int page, CafeSearchRequest request) {
        //1. 아이돌 2. 아이돌 + 날짜 3. 아이돌 + 장소 4. 아이돌 + 날짜 + 장소 검색
        List<Cafe> cafes = cafeRepository.getCafeResults(page, request);
        return cafes.stream().map(c -> CafeSearchResponse.builder()
                        .cafeName(c.getCafeName())
                        .address(c.getAddress())
                        .imageUrl(c.getImageUrl())
                        .build())
                .collect(toList());
    }

    public CafeResponse getCafeDetails(Long cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(CafeNotFound::new);

        return CafeResponse.builder()
                .cafeId(cafe.getId())
                .cafeName(cafe.getCafeName())
                .introduction(cafe.getIntroduction())
                .imageUrl(cafe.getImageUrl())
                .build();
    }

}
