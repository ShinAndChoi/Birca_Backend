package com.app.birca.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class FavoriteIdol {

    @Id @GeneratedValue
    @Column(name = "favorite_idol_id")
    private Long id;

    private String idolName;

    public FavoriteIdol(String idolName) {
        this.idolName = idolName;
    }

}
