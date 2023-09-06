package com.app.birca.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReservedCafeResponse {

    private final Long cafeId;
    private final String cafeName;
    private final String introduction;
    private final String address;

    @Builder
    public ReservedCafeResponse(Long cafeId, String cafeName, String introduction, String address) {
        this.cafeId = cafeId;
        this.cafeName = cafeName;
        this.introduction = introduction;
        this.address = address;
    }

}
