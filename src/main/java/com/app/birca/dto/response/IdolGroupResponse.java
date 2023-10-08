package com.app.birca.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class IdolGroupResponse {

    private final String koreanName;
    private final String englishName;
    private final String imageUrl;

    @Builder
    public IdolGroupResponse(String koreanName, String englishName, String imageUrl) {
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.imageUrl = imageUrl;
    }

}
