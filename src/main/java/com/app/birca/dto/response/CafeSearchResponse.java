package com.app.birca.dto.response;

import com.app.birca.domain.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CafeSearchResponse {
    private final String cafeName;
    private final Address address;
    private final String imageUrl;

    @Builder
    public CafeSearchResponse(String cafeName, Address address, String imageUrl) {
        this.cafeName = cafeName;
        this.address = address;
        this.imageUrl = imageUrl;
    }

}
