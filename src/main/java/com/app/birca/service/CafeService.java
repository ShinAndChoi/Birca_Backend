package com.app.birca.service;

import com.app.birca.domain.entity.*;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.request.SaveCafeRequest;
import com.app.birca.dto.request.UpdateCafeRequest;
import com.app.birca.dto.response.CafeResponse;
import com.app.birca.exception.CafeNotFound;
import com.app.birca.exception.UserNotFound;
import com.app.birca.repository.CafeImageRepository;
import com.app.birca.repository.CafeRepository;
import com.app.birca.repository.ReservationAvailabilityRepository;
import com.app.birca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CafeService {

    private final BusinessLicenseService businessLicenseService;
    private final ReservationAvailabilityRepository reservationAvailabilityRepository;
    private final CafeImageRepository cafeImageRepository;
    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;
    private final S3Service s3Service;

    @Transactional
    public Long saveCafe(LoginUser loginUser, SaveCafeRequest request) throws IOException {
        User user = userRepository.findById(loginUser.getId())
                .orElseThrow(UserNotFound::new);

        Cafe cafe = Cafe.builder()
                .cafeName(request.getCafeName())
                .ownerName(request.getOwnerName())
                .introduction(request.getIntroduction())
                .xAxis(request.getxAxis())
                .yAxis(request.getyAxis())
                .area(request.getArea())
                .capacity(request.getCapacity())
                .price(request.getPrice())
                .contact(request.getContact())
                .user(user)
                .build();

        cafeRepository.save(cafe);

        MultipartFile businessLicense = request.getBusinessLicense();
//        businessLicenseService.uploadBusinessLicense(businessLicense);
//        BusinessLicenseResponse businessLicenseInfo = ocrService.getBusinessLicenseInfo(businessLicense, "사업자 등록증");
//        businessLicenseService.saveRegistrationNumber(loginUser, businessLicenseInfo);

        saveCafeImages(request, cafe);
        saveReservationAvailability(request, cafe);

        return cafe.getId();
    }

    private void saveReservationAvailability(SaveCafeRequest request, Cafe cafe) {
        List<LocalDate> localDates = request.getLocalDates();
        for (LocalDate localDate : localDates) {
            ReservationAvailability reservationAvailability = new ReservationAvailability(localDate, cafe);
            reservationAvailabilityRepository.save(reservationAvailability);
        }
    }

    private void saveCafeImages(SaveCafeRequest request, Cafe cafe) throws IOException {
        List<MultipartFile> cafeImages = request.getCafeImages();
        for (MultipartFile image : cafeImages) {
            String imageUrl = s3Service.uploadImage(image);
            CafeImage cafeImage = new CafeImage(imageUrl, cafe);
            cafeImageRepository.save(cafeImage);
        }
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

}
