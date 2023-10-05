package com.app.birca.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class IdolGroup {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idol_group_id")
    private Long id;

    private String groupName;

    public IdolGroup(String groupName) {
        this.groupName = groupName;
    }

    @OneToMany(mappedBy = "idolGroup", cascade = ALL)
    private List<Idol> idols = new ArrayList<>();

}
