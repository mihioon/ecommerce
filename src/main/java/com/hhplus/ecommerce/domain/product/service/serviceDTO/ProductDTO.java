package com.hhplus.ecommerce.domain.product.service.serviceDTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDTO {
    private Long productId;
    private String productNm;
    private BigDecimal salePrice;
    private Long stockQuantity;
}
