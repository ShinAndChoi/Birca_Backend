package com.app.birca.domain;

import lombok.Getter;

@Getter
public enum RoleType {

    NORMAL("normal"), CAFE_OWNER("cafeOwner"), ADMIN("admin");

    private String type;

    RoleType(String type) {
        this.type = type;
    }

    public static RoleType fromString(String value) {
        for (RoleType type : RoleType.values()) {
            if (type.getType().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 등급입니다.");
    }

}
