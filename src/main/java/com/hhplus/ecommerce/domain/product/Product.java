package com.hhplus.ecommerce.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Product {
    private Long id; /* Key */
    private String productNm; /* 제품명 */
    private BigDecimal salePrice; /* 제품금액 */
    private Detail productDetail; /* 제품상세정보 */

    @Getter
    @AllArgsConstructor
    public static class Detail {
        private Long productId;
        private Long quantity;
        private Long likeCnt;
        private Long dayOrderCnt;
    }

    public void assembleWithDetail(Detail productDetail) {
        this.productDetail = productDetail;
    }
}
