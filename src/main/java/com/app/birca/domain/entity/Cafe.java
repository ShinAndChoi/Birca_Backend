package com.app.birca.domain.entity;

import com.app.birca.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Cafe extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cafe_id")
    private Long id;

    private String cafeName;
    private String introduction;
    private String imageUrl;
    private String address;
    private String contact;

    @OneToMany(mappedBy = "cafe", cascade = ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Cafe(String cafeName, String introduction, String imageUrl, String address, String contact, User user) {
        this.cafeName = cafeName;
        this.introduction = introduction;
        this.imageUrl = imageUrl;
        this.address = address;
        this.contact = contact;
        this.user = user;
    }

    public void updateCafe(String cafeName, String introduction, String imageUrl, String address, String contact, User user) {
        this.cafeName = cafeName;
        this.introduction = introduction;
        this.imageUrl = imageUrl;
        this.address = address;
        this.contact = contact;
        this.user = user;
    }

}
