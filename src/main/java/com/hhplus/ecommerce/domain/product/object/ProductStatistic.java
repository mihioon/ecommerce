package com.hhplus.ecommerce.domain.product.object;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductStatistic {
    private Long productId; /* Key */
    private Long checkOutCnt; /* 구매횟수 */
    private Long cartCnt; /* 카트횟수 */
    private Long prodClickCnt; /* 상품클릭횟수 */
    private Long searchCnt; /* 검색횟수 */
}
