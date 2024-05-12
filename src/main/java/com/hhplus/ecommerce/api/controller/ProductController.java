package com.hhplus.ecommerce.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    // 상품 조회 api
    @GetMapping("/{productId}")
    public ResponseEntity<?> searchProductInfo(@PathVariable String param) {
        return null;
    }

    // 인기 판매 상품 조회 api
    @GetMapping("/popularItem")
    public ResponseEntity<?> searchPopularItem() {
        return null;
    }



}
