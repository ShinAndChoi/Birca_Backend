package com.app.birca.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CafeSearchRequest {
    @NotBlank(message = "아이돌 입력은 필수사항입니다.")
    private String idolName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String address;
}
