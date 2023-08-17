package com.app.birca.domain;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class Address {
    //차례로 시, 구, 동 ex) 서울시 구로구 개봉동
    private String city;
    private String district;
    private String area;

    @Builder
    public Address(String city, String district, String area) {
        this.city = city;
        this.district = district;
        this.area = area;
    }

}
