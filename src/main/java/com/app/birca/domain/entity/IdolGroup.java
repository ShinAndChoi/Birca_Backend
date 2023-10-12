package com.app.birca.domain.entity;

import com.app.birca.domain.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
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

    @Enumerated(STRING)
    private Category category;

    public IdolGroup(String koreanName, String englishName, String imageUrl, Category category) {
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.imageUrl = imageUrl;
        this.category = category;
    }

}
