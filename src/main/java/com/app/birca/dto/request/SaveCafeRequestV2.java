package com.app.birca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaveCafeRequestV2 {
    private String cafeName;
    private String introduction;
    private String address;
    private String contact;
}
