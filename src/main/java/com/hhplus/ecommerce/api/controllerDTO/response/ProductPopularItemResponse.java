package com.hhplus.ecommerce.api.controllerDTO.response;

import com.hhplus.ecommerce.domain.product.Product;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class ProductPopularItemResponse {
    private Long id; /* Key */
    private String productNm; /* 제품명 */
    private BigDecimal salePrice; /* 제품금액 */
    private Product.Detail productDetail;

    public void toResponse(Product product){
        this.id = product.getId();
        this.productNm = product.getProductNm();
        this.salePrice = product.getSalePrice();
        this.productDetail = product.getProductDetail();
    }
}
