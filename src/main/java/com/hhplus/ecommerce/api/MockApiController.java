package com.hhplus.ecommerce.api;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MockApiController {

    // 잔액충전 / 조회 api
    @GetMapping("/customer/mock-amount")
    public ResponseEntity<?> mockApi() {
        Map<String, Object> response = new HashMap<>();

        ChargeAmountResponse body = new ChargeAmountResponse(1L, "1000");

        response.put("code", "SUCCESS");
        response.put("message", "충전되었습니다.");
        response.put("body", body);

        return ResponseEntity.ok(response);
    }

    // 상품 클래스 (내부 클래스로 정의)
    @Data
    private static class ChargeAmountResponse {
        private Long customerId;
        private String amount;

        public ChargeAmountResponse(Long customerId, String amount) {
            this.customerId = customerId;
            this.amount = amount;
        }
    }

    // 상품 조회 api
    @GetMapping("/store/mock-product/{productId}")
    public ResponseEntity<?> selectProduct() {
        Map<String, Object> response = new HashMap<>();

        selectProductResponse body = new selectProductResponse(1L, "상품1", "1000", 1L);

        response.put("code", "SUCCESS");
        response.put("message", "충전되었습니다.");
        response.put("body", body);

        return ResponseEntity.ok(response);
    }

    // 상품 클래스 (내부 클래스로 정의)
    @Data
    private static class selectProductResponse {
        private Long productId;
        private String productNm;
        private String salePrice;
        private Long stockQuantity;

        public selectProductResponse(Long productId, String productNm, String salePrice, Long stockQuantity) {
            this.productId = productId;
            this.productNm = productNm;
            this.salePrice = salePrice;
            this.stockQuantity = stockQuantity;
        }
    }

    // 주문 / 결제 api
    @GetMapping("/payment/mock-checkOut")
    public ResponseEntity<?> checkOut() {
        Map<String, Object> response = new HashMap<>();

        CheckOutResponse body = new CheckOutResponse(1000L, "상품1" , 1L);

        response.put("code", "SUCCESS");
        response.put("message", "충전되었습니다.");
        response.put("body", body);

        return ResponseEntity.ok(response);
    }

    // 상품 클래스 (내부 클래스로 정의)
    @Data
    private static class CheckOutResponse {
        private Long productId;
        private String productNm;
        private Long quantity;

        public CheckOutResponse(Long productId, String productNm, Long quantity) {
            this.productId = productId;
            this.productNm = productNm;
            this.quantity = quantity;
        }
    }

    // 인기 판매 상품 조회 api
    @GetMapping("/product/mock-popularItem")
    public ResponseEntity<?> selectPopularItem() {
        Map<String, Object> response = new HashMap<>();

        List<PopularItemResponse> body = new ArrayList<>();
        PopularItemResponse item1 = new PopularItemResponse(1000L, "상품1", "1000");
        PopularItemResponse item2 = new PopularItemResponse(2000L, "상품2", "2000");
        body.add(item1);
        body.add(item2);

        response.put("body", body);

        return ResponseEntity.ok(response);
    }

    // 상품 클래스 (내부 클래스로 정의)
    @Data
    private static class PopularItemResponse {
        private Long productId;
        private String productNm;
        private String salePrice;

        public PopularItemResponse(Long productId, String productNm, String salePrice) {
            this.productId = productId;
            this.productNm = productNm;
            this.salePrice = salePrice;
        }
    }
}
