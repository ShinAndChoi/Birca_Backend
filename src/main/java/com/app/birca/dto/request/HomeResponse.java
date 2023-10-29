package com.app.birca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HomeResponse<T> {
    private T homeResponse;
}
