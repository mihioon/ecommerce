package com.hhplus.ecommerce.api;

public class ApiResponse<T> {
    private String code;
    private String message;
    private T body;

    public ApiResponse(String code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public ApiResponse(String message, T body){
        this.code = "SUCCESS";
        this.message = message;
        this.body = body;
    }

    public ApiResponse(String message) {
        this.code = "FAIL";
        this.message = message;
    }
}
