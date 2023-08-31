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

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeService {

    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;
    private final S3Service s3Service;

    @Transactional
    public Long saveCafe(LoginUser loginUser, SaveCafeRequest request) throws IOException {
        User user = userRepository.findById(loginUser.getId())
                .orElseThrow(UserNotFound::new);

        String imageUrl = s3Service.uploadImage(request.getFile());

        Cafe cafe = Cafe.builder()
                .cafeName(request.getCafeName())
                .introduction(request.getIntroduction())
                .imageUrl(imageUrl)
                .address(request.getAddress())
                .contact(request.getContact())
                .user(user)
                .build();

        return cafeRepository.save(cafe).getId();
    }

    //카페 수정
    @Transactional
    public void updateCafe(Long cafeId, UpdateCafeRequest request) throws IOException {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(CafeNotFound::new);

        User user = cafe.getUser();

        String imageUrl = s3Service.uploadImage(request.getFile());

        cafe.updateCafe(request.getCafeName(), request.getIntroduction(),
                imageUrl, request.getAddress(), request.getContact(), user);
    }

    //카페 상세 페이지
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

    //캘린더에서 클릭한 날짜에 대한 카페 검색
    public List<CafeSearchResponse> findBySelectedDate(LocalDate selectedDate) {
        List<Cafe> cafes = cafeRepository.findBySelectedDate(selectedDate);
        return cafes.stream().map(c -> CafeSearchResponse.builder()
                        .cafeName(c.getCafeName())
                        .address(c.getAddress())
                        .imageUrl(c.getImageUrl())
                        .build())
                .collect(toList());
    }

}
