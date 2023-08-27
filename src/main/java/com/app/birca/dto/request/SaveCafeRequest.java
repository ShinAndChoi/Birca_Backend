package com.app.birca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class SaveCafeRequest {
    private String cafeName;
    private String introduction;
    private String address;
    private MultipartFile file;
}
