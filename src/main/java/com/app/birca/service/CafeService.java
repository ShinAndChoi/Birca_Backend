package com.app.birca.service;

import com.app.birca.domain.entity.Cafe;
import com.app.birca.domain.entity.CafeImage;
import com.app.birca.domain.entity.User;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.request.SaveCafeRequest;
import com.app.birca.dto.request.SaveCafeRequestV2;
import com.app.birca.dto.request.UpdateCafeRequest;
import com.app.birca.dto.response.CafeResponse;
import com.app.birca.dto.response.CafeSearchResponse;
import com.app.birca.exception.CafeNotFound;
import com.app.birca.exception.UserNotFound;
import com.app.birca.repository.CafeImageRepository;
import com.app.birca.repository.CafeRepository;
import com.app.birca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeService {

    private final CafeImageRepository cafeImageRepository;
    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;
    private final S3Service s3Service;

    @Transactional
    public Long saveCafe(LoginUser loginUser, SaveCafeRequest request, List<MultipartFile> cafeImages) throws IOException {
        User user = userRepository.findById(loginUser.getId())
                .orElseThrow(UserNotFound::new);

        Cafe cafe = Cafe.builder()
                .cafeName(request.getCafeName())
                .introduction(request.getIntroduction())
                .address(request.getAddress())
                .contact(request.getContact())
                .user(user)
                .build();

        cafeRepository.save(cafe);

        for (MultipartFile image : cafeImages) {
            String imageUrl = s3Service.uploadImage(image);
            CafeImage cafeImage = new CafeImage(imageUrl, cafe);
            cafeImageRepository.save(cafeImage);
        }

        return cafe.getId();
    }

    @Transactional
    public Long saveCafeV2(LoginUser loginUser, SaveCafeRequestV2 request, List<MultipartFile> cafeImages) throws IOException {
        User user = userRepository.findById(loginUser.getId())
                .orElseThrow(UserNotFound::new);

        Cafe cafe = Cafe.builder()
                .cafeName(request.getCafeName())
                .introduction(request.getIntroduction())
                .address(request.getAddress())
                .contact(request.getContact())
                .user(user)
                .build();

        cafeRepository.save(cafe);

        for (MultipartFile image : cafeImages) {
            String imageUrl = s3Service.uploadImage(image);
            CafeImage cafeImage = new CafeImage(imageUrl, cafe);
            cafeImageRepository.save(cafeImage);
        }

        return cafe.getId();
    }

    //카페 수정
    @Transactional
    public void updateCafe(Long cafeId, UpdateCafeRequest request, List<MultipartFile> cafeImages) throws IOException {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(CafeNotFound::new);

        User user = cafe.getUser();

        //기존 이미지 삭제
        List<CafeImage> images = cafe.getCafeImages();
        cafeImageRepository.deleteAllInBatch(images);

        //새로운 이미지 저장
        for (MultipartFile image : cafeImages) {
            String imageUrl = s3Service.uploadImage(image);
            CafeImage newCafeImage = new CafeImage(imageUrl, cafe);
            cafeImageRepository.save(newCafeImage);
            cafe.getCafeImages().add(newCafeImage);
        }

        cafe.updateCafe(request.getCafeName(), request.getIntroduction(),
                request.getAddress(), request.getContact(), user);
    }

    //카페 상세 페이지
    public CafeResponse getCafeDetails(Long cafeId) {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(CafeNotFound::new);

        return CafeResponse.builder()
                .cafeId(cafe.getId())
                .cafeName(cafe.getCafeName())
                .introduction(cafe.getIntroduction())
                .cafeImages(cafe.getCafeImages())
                .build();
    }

    //캘린더에서 클릭한 날짜에 대한 카페 검색
    public List<CafeSearchResponse> findBySelectedDate(LocalDate selectedDate) {
        List<Cafe> cafes = cafeRepository.findBySelectedDate(selectedDate);
        return cafes.stream().map(c -> CafeSearchResponse.builder()
                        .cafeName(c.getCafeName())
                        .address(c.getAddress())
                        .build())
                .collect(toList());
    }

}
