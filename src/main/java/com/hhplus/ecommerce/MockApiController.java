package com.hhplus.ecommerce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockApiController {

    @GetMapping("/v1/user/{userId}")
    public String getUserById(@PathVariable String userId) {
        // 하드코딩된 사용자 정보 반환
        return "헬로우 월드";
    }

    @GetMapping("/v1/product/{productId}")
    public String getProductById(@PathVariable String productId) {
        // 하드코딩된 제품 정보 반환
        return "Product Name: Example Product, Price: 1000";
    }
}
