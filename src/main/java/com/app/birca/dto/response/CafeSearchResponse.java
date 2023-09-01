package com.app.birca.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CafeSearchResponse {

    private final Long cafeId;
    private final String cafeName;
    private final String introduction;
    private final String address;

    @Builder
    public CafeSearchResponse(Long cafeId, String cafeName, String introduction, String address) {
        this.cafeId = cafeId;
        this.cafeName = cafeName;
        this.introduction = introduction;
        this.address = address;
    }
}
