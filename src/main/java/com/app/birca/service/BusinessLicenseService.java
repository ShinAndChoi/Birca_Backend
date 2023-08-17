package com.app.birca.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.app.birca.config.S3Config;
import com.app.birca.domain.entity.BusinessLicense;
import com.app.birca.domain.entity.User;
import com.app.birca.dto.request.LoginUser;
import com.app.birca.dto.response.businesslicense.BusinessLicenseResponse;
import com.app.birca.dto.response.businesslicense.BusinessLicenseStatus;
import com.app.birca.exception.BusinessRegistrationError;
import com.app.birca.exception.DuplicationRegistrationNumber;
import com.app.birca.exception.UserNotFound;
import com.app.birca.repository.BusinessLicenseRepository;
import com.app.birca.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessLicenseService {

    private final NationalTaxService nationalTaxService;
    private final BusinessLicenseRepository businessLicenseRepository;
    private final UserRepository userRepository;
    private final S3Config s3Config;
    private final AmazonS3Client amazonS3;

    public void uploadBusinessLicense(MultipartFile file) throws IOException {
        String s3FileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(file.getInputStream().available());
        amazonS3.putObject(s3Config.getBucket(), s3FileName, file.getInputStream(), objMeta);

        amazonS3.getUrl(s3Config.getBucket(), s3FileName).toString();
    }

    public void saveRegistrationNumber(LoginUser loginUser, BusinessLicenseResponse businessLicenseResponse) {
        String registrationNumber = getRegisterNumber(businessLicenseResponse);
        if (businessLicenseRepository.existsByRegistrationNumber(registrationNumber)) {
            throw new DuplicationRegistrationNumber();
        }

        BusinessLicenseStatus businessLicenseStatus = nationalTaxService.confirmBusinessLicenseStatus(registrationNumber);
        String taxType = getTaxType(businessLicenseStatus);
        if (taxType.equals("국세청에 등록되지 않은 사업자등록번호입니다.")) {
            throw new BusinessRegistrationError();
        }

        User user = userRepository.findById(loginUser.getId())
                .orElseThrow(UserNotFound::new);

        BusinessLicense businessLicense = BusinessLicense.builder()
                .registrationNumber(registrationNumber)
                .user(user)
                .isRegisteredBusiness(false)
                .build();

        businessLicenseRepository.save(businessLicense);
    }

    private String getRegisterNumber(BusinessLicenseResponse businessLicenseResponse) {
        //사업자등록증 번호가 610-81-54020이면 -> 6108154020
        return businessLicenseResponse.getImages().get(0).getBizLicense().getResult()
                .getRegisterNumber().get(0).getText().replace("-", "");
    }

    private String getTaxType(BusinessLicenseStatus businessLicenseStatus) {
        return businessLicenseStatus.getData().get(0).getTax_type();
    }

}
