package com.app.birca.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class IdolResponse {

    private final String koreanName;
    private final String englishName;
    private final String imageUrl;

    @Builder
    public IdolResponse(String koreanName, String englishName, String imageUrl) {
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.imageUrl = imageUrl;
    }
}
