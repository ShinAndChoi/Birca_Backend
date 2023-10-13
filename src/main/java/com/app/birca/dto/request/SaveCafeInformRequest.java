package com.app.birca.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SaveCafeInformRequest {
    private String ownerName;
    private String cafeName;
    private String contactNumber;
    private MultipartFile businessLicense;
}
