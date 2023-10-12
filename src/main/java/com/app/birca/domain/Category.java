package com.app.birca.domain;

import lombok.Getter;

@Getter
public enum Category {

    BOY("보이그룹"), GIRL("걸그룹"), SOLO("솔로"), MIXED("혼성");

    private String type;

    Category(String type) {
        this.type = type;
    }

    public static Category fromString(String value) {
        for (Category type : Category.values()) {
            if (type.getType().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 카테고리입니다.");
    }

}
