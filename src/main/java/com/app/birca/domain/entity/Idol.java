package com.app.birca.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Idol {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idol_id")
    private Long id;

    private String koreanName;
    private String englishName;
    private String imageUrl;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "idol_group_id")
    private IdolGroup idolGroup;

    public Idol(String koreanName, String englishName, String imageUrl, IdolGroup idolGroup) {
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.imageUrl = imageUrl;
        this.idolGroup = idolGroup;
    }

}
