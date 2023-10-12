package com.app.birca.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class InsertIdolGroupRequest {
    private String koreanName;
    private String englishName;
    private String type;
    private MultipartFile multipartFile;
}
