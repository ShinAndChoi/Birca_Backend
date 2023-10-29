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

    private String ownerName;
    private String cafeName;
    private String introduction;
    private String contact;
    private int capacity;
    private int price;
    private Double xAxis;
    private Double yAxis;
    private String area;

    @OneToMany(mappedBy = "cafe", cascade = ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "cafe", cascade = ALL)
    private List<CafeImage> cafeImages = new ArrayList<>();

    @OneToMany(mappedBy = "cafe", cascade = ALL)
    private List<ReservationAvailability> reservationAvailabilities = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Cafe(String ownerName, String cafeName, String introduction, String contact,
                int capacity, int price, Double xAxis, Double yAxis, String area, User user) {
        this.ownerName = ownerName;
        this.cafeName = cafeName;
        this.introduction = introduction;
        this.contact = contact;
        this.capacity = capacity;
        this.price = price;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.area = area;
        this.user = user;
    }

    public void updateCafe(String ownerName, String cafeName, String introduction,
                           String contact, int capacity, int price, User user) {
        this.cafeName = cafeName;
        this.introduction = introduction;
        this.contact = contact;
        this.capacity = capacity;
        this.price = price;
    }

}
