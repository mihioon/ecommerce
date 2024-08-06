package com.hhplus.ecommerce.api.controller;

import com.hhplus.ecommerce.api.ApiResponse;
import com.hhplus.ecommerce.api.SuccessType;
import com.hhplus.ecommerce.api.controllerDTO.response.ProductInfoResponse;
import com.hhplus.ecommerce.domain.product.Product;
import com.hhplus.ecommerce.domain.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    // 인기 판매 상품 조회 api
    @GetMapping("/popularItem")
    public ResponseEntity<?> searchPopularItem() {
        return null;
    }

    // 상품 조회 api
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductInfoResponse>> findProduct(@PathVariable Long id) {
        Product result = productService.findProduct(id);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.msg, new ProductInfoResponse(result)));
    }

}
