package com.app.birca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class SaveCafeRequest {
    private String cafeName;
    private String introduction;
    private String address;
    private String contact;
    private MultipartFile businessLicense;
    private List<MultipartFile> cafeImages;
}
