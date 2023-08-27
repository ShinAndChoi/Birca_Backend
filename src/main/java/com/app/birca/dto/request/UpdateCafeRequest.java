package com.app.birca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class UpdateCafeRequest {
    private String cafeName;
    private String introduction;
    private String address;
    private MultipartFile file;
}
