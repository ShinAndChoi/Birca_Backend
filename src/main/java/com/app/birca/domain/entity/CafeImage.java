package com.app.birca.domain.entity;

import com.app.birca.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class CafeImage extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cafe_image_id")
    private Long id;

    private String imageUrl;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    public CafeImage(String imageUrl, Cafe cafe) {
        this.imageUrl = imageUrl;
        this.cafe = cafe;
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
