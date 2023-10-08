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
public class FavoriteIdol {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "favorite_idol_id")
    private Long id;

    private String idolName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public FavoriteIdol(String idolName, User user) {
        this.idolName = idolName;
        this.user = user;
    }

}
