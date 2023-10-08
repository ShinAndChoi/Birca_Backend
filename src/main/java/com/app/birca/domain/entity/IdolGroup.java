package com.app.birca.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class IdolGroup {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idol_group_id")
    private Long id;

    private String koreanName;
    private String englishName;
    private String imageUrl;

    public IdolGroup(String koreanName, String englishName, String imageUrl) {
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.imageUrl = imageUrl;
    }

}
