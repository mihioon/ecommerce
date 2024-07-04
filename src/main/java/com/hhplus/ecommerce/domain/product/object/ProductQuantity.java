package com.hhplus.ecommerce.domain.product.object;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductQuantity {
    private Long id; /* Key */
    private Long quantity; /* 제품수량 */
}
