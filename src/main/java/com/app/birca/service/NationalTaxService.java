package com.app.birca.service;

import com.app.birca.config.WebClientConfig;
import com.app.birca.dto.request.BusinessLicenseRequest;
import com.app.birca.dto.response.businesslicense.BusinessLicenseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
@RequiredArgsConstructor
public class NationalTaxService {

    private final WebClientConfig webClientConfig;

    public BusinessLicenseStatus confirmBusinessLicenseStatus(String registerNumber) {
        BusinessLicenseRequest request = new BusinessLicenseRequest(List.of(registerNumber));

        return webClientConfig.confirmRegisterNumber().post()
                .contentType(APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(BusinessLicenseStatus.class)
                .blockLast();
    }

}
