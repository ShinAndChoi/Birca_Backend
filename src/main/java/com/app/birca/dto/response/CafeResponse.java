package com.app.birca.dto.response;

import com.app.birca.domain.entity.CafeImage;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CafeResponse {

    private final Long cafeId;
    private final String cafeName;
    private final String introduction;
    private final List<String> imageUrl;

    @Builder
    public CafeResponse(Long cafeId, String cafeName, String introduction, List<CafeImage> cafeImages) {
        this.cafeId = cafeId;
        this.cafeName = cafeName;
        this.introduction = introduction;
        this.imageUrl = cafeImages.stream().map(i -> i.getImageUrl())
                .collect(Collectors.toList());
    }

}
