package com.app.birca.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MyCafeResponse {

    private final Long cafeId;
    private final String cafeName;
    private final String address;
    private final String introduction;
    private final List<String> imageUrl;

    @Builder
    public MyCafeResponse(Long cafeId, String cafeName, String address, String introduction, List<String> imageUrl) {
        this.cafeId = cafeId;
        this.cafeName = cafeName;
        this.address = address;
        this.introduction = introduction;
        this.imageUrl = imageUrl;
    }

}
