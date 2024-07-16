package com.hhplus.ecommerce.api.controllerDTO.request;

import com.hhplus.ecommerce.domain.order.OrderProduct;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class OrderProductsRequest {
    private List<OrderProductRequest>  list;

    static class OrderProductRequest{
        private Long productId;
        private Long quantity;
        private BigDecimal prodTotalPrice;

        // Getter
        public Long getProductId() {
            return productId;
        }
        public Long getQuantity() {
            return quantity;
        }
        public BigDecimal getProdTotalPrice() {
            return prodTotalPrice;
        }
    }

    public List<OrderProduct> toDomain(){
        return this.list.stream()
                .map(orderProduct -> new OrderProduct(
                        orderProduct.getProductId(),
                        orderProduct.getQuantity(),
                        orderProduct.getProdTotalPrice()
                )).toList();
    }
}
