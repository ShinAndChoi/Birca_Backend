package com.app.birca.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CafeReservationRequest {
    @NotBlank(message = "아이돌 입력은 필수사항입니다.")
    private String idolName;
    private LocalDate startDate;
    private LocalDate endDate;
}
