package com.app.birca.controller;

import com.app.birca.config.Login;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.request.SaveCafeRequest;
import com.app.birca.dto.request.UpdateCafeRequest;
import com.app.birca.dto.response.CafeIdResponse;
import com.app.birca.dto.response.CafeResponse;
import com.app.birca.service.BusinessLicenseService;
import com.app.birca.service.CafeService;
import com.app.birca.service.OcrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CafeController {

    private final BusinessLicenseService businessLicenseService;
    private final CafeService cafeService;
    private final OcrService ocrService;

    @PostMapping("/cafe/register")
    public CafeIdResponse saveCafe(@Login LoginUser loginUser, @ModelAttribute SaveCafeRequest request) throws IOException {
        log.info("cafeName = {} ", request.getCafeName());
        log.info("introduction = {} ", request.getIntroduction());
        log.info("cafeImages = {}", request.getCafeImages().get(0).getOriginalFilename());
        log.info("contact = {}", request.getContact());

        //businessLicenseService.uploadBusinessLicense(businessLicense);
        //BusinessLicenseResponse businessLicenseInfo = ocrService.getBusinessLicenseInfo(businessLicense, "사업자 등록증");
        //businessLicenseService.saveRegistrationNumber(loginUser, businessLicenseInfo);

        Long cafeId = cafeService.saveCafe(loginUser, request);
        return new CafeIdResponse(cafeId);
    }

    @PatchMapping("/cafe/{cafeId}/update")
    public void updateCafe(@PathVariable Long cafeId, @RequestPart UpdateCafeRequest request,
                           @RequestPart List<MultipartFile> cafeImages) throws IOException {
        log.info("cafeName = {} ", request.getCafeName());
        log.info("introduction = {} ", request.getIntroduction());
        cafeService.updateCafe(cafeId, request, cafeImages);
    }

    @GetMapping("/cafe/{cafeId}/details")
    public CafeResponse getCafeDetails(@PathVariable Long cafeId) {
        return cafeService.getCafeDetails(cafeId);
    }

}
