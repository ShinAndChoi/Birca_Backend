package com.app.birca.domain.entity;

import com.app.birca.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class BusinessLicense extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "business_license_id")
    private Long id;

    private String registrationNumber;
    private Boolean isRegisteredBusiness;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public BusinessLicense(String registrationNumber, Boolean isRegisteredBusiness, User user) {
        this.registrationNumber = registrationNumber;
        this.isRegisteredBusiness = isRegisteredBusiness;
        this.user = user;
    }

    public void updateRegisteredBusiness(Boolean registeredBusiness) {
        isRegisteredBusiness = registeredBusiness;
    }

}
