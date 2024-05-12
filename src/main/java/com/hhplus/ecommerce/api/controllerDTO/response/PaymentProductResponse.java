package com.hhplus.ecommerce.api.controllerDTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentProductResponse {
    private Long productId;
    private String productNm;
    private BigDecimal salePrice;
    private Long quantity;
}
