package com.app.birca.domain.entity;

import com.app.birca.domain.BaseEntity;
import com.app.birca.domain.RoleType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Enumerated(STRING)
    private RoleType roleType;

    private String nickname;
    private String email;

    @OneToMany
    private List<FavoriteIdol> favoriteIdols = new ArrayList<>();

    @Builder
    public User(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public void updateRoleType(String roleType) {
        this.roleType = RoleType.fromString(roleType);
    }

}
