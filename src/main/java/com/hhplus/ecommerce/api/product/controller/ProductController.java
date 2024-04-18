package com.hhplus.ecommerce.api.product.controller;

import com.hhplus.ecommerce.api.product.controllerDTO.ProductInfoResponse;
import com.hhplus.ecommerce.api.product.controllerDTO.ProductPopularItemResponse;
import com.hhplus.ecommerce.business.product.service.ProductService;
import com.hhplus.ecommerce.exception.product.ProductsNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductDTOmapper mapper;

    public ProductController(ProductService productService, ProductDTOmapper mapper){
        this.productService = productService;
        this.mapper = mapper;
    }
    // 상품 조회 api
    @GetMapping("/{productId}")
    public ResponseEntity<?> searchProductInfo(@PathVariable String param) {
        try {
            Long productId = mapper.convertProductId(param);

            ProductInfoResponse result = ProductInfoResponse.builder()
                    .code("SUCCESS")
                    .message("조회되었습니다.")
                    .body(productService.searchProduct(productId))
                    .build();
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e){
            ProductInfoResponse errorResult = ProductInfoResponse.builder()
                    .code("ERROR")
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorResult);
        }
    }

    // 인기 판매 상품 조회 api
    @GetMapping("/popularItem")
    public ResponseEntity<?> searchPopularItem() {
        try {
            ProductPopularItemResponse result = ProductPopularItemResponse.builder()
                    .code("SUCCESS")
                    .message("조회되었습니다.")
                    .body(productService.searchProductStatistic())
                    .build();
            return ResponseEntity.ok(result);
        } catch (ProductsNotFoundException e){
            ProductPopularItemResponse errorResult = ProductPopularItemResponse.builder()
                    .code("ERROR")
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.ok(errorResult);
        }
    }



}
