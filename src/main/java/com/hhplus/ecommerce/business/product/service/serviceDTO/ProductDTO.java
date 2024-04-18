package com.hhplus.ecommerce.business.product.service.serviceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productNm;
    private BigDecimal salePrice;
    private Long stockQuantity;

    public ProductDTO(Long productId, BigDecimal salePrice, Long stockQuantity){
        this.productId = productId;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
    }
}
