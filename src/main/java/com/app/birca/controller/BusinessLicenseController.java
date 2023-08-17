package com.app.birca.controller;

import com.app.birca.config.Login;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.response.businesslicense.BusinessLicenseResponse;
import com.app.birca.service.BusinessLicenseService;
import com.app.birca.service.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class BusinessLicenseController {

    private final BusinessLicenseService businessLicenseService;
    private final OcrService ocrService;

    @PostMapping("/businessLicense/save")
    public void get(@Login LoginUser loginUser, @RequestParam("file") MultipartFile file) throws IOException {
        businessLicenseService.uploadBusinessLicense(file);
        BusinessLicenseResponse businessLicenseInfo = ocrService.getBusinessLicenseInfo(file, "사업자 등록증");
        businessLicenseService.saveRegistrationNumber(loginUser, businessLicenseInfo);
    }

}
