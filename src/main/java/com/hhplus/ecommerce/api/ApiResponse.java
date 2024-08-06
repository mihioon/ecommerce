package com.hhplus.ecommerce.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.lang.model.type.ErrorType;

@Getter
@JsonPropertyOrder({"code", "message", "data"})
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static ApiResponse<?> success(SuccessType successType) {
        return new ApiResponse<>(successType.getMessage());
    }

    public static <T> ApiResponse<T> success(SuccessType successType, T data) {
        return new ApiResponse<T>(successType.getMessage(), data);
    }

    public static ApiResponse<?> error(ErrorType errorType) {
        return new ApiResponse<>("오류");
    }

    public static ApiResponse<?> error(ErrorType errorType, String message) {
        return new ApiResponse<>(message);
    }

    public static <T> ApiResponse<T> error(ErrorType errorType, String message, T data) {
        return new ApiResponse<>(message, data);
    }
}
