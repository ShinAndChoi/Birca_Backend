package com.app.birca.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class InsertIdolRequest {
    private String koreanName;
    private List<IdolNameRequest> idols;
    private List<MultipartFile> files;
}
