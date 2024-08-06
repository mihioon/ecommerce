package com.hhplus.ecommerce.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessType {

    msg("성공");

    private final String message;
}
