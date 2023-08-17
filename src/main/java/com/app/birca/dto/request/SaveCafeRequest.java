package com.app.birca.dto.request;

import com.app.birca.domain.Address;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class SaveCafeRequest {
    private String cafeName;
    private String introduction;
    private MultipartFile file;
    private Address address;
}
