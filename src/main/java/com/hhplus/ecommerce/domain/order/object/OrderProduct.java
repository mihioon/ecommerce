package com.hhplus.ecommerce.domain.order.object;

import com.hhplus.ecommerce.domain.product.object.ProductQuantity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OrderProduct {
    private Long productId;
    private Long quantity;
    private BigDecimal prodTotalPrice;

    public ProductQuantity toStockModel(OrderProduct orderProduct){
        return new ProductQuantity(orderProduct.getProductId(), orderProduct.getQuantity());

    }
}
