package com.app.birca.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdateCafeRequest {
    private String cafeName;
    private String introduction;
    private MultipartFile file;
}
