package com.app.birca.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CafeSearchResponse {

    private final String cafeName;
    private final String address;
    private final String imageUrl;

    @Builder
    public CafeSearchResponse(String cafeName, String address, String imageUrl) {
        this.cafeName = cafeName;
        this.address = address;
        this.imageUrl = imageUrl;
    }

}
