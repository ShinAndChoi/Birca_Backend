package com.app.birca.dto.response.businesslicense;

import lombok.Getter;

import java.util.List;

@Getter
public class BusinessLicenseResponse {
    private String version;
    private String requestId;
    private long timestamp;
    private List<ImageInfo> images;
}
