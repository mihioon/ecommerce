package com.hhplus.ecommerce.api.controllerDTO.response;

import com.hhplus.ecommerce.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductInfoResponse {

    private Long id; /* Key */
    private String product_nm; /* 제품명 */
    private BigDecimal sale_price; /* 제품금액 */
    private Product.Detail product_detail;

    public ProductInfoResponse(Product product){
        this.id = product.getId();
        this.product_nm = product.getProductNm();
        this.sale_price = product.getSalePrice();
        this.product_detail = product.getProductDetail();
    }
}
