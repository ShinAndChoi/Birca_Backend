package com.app.birca.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CafeResponse {

    private final Long cafeId;
    private final String cafeName;
    private final String introduction;
    private final String imageUrl;

    @Builder
    public CafeResponse(Long cafeId, String cafeName, String introduction, String imageUrl) {
        this.cafeId = cafeId;
        this.cafeName = cafeName;
        this.introduction = introduction;
        this.imageUrl = imageUrl;
    }

}
