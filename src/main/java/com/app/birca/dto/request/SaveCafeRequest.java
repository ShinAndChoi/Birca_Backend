package com.app.birca.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SaveCafeRequest {
    private String cafeName;
    private String introduction;
    private String address;
    private MultipartFile file;
}
