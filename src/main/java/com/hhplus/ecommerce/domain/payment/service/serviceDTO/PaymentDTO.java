package com.hhplus.ecommerce.domain.payment.service.serviceDTO;

import com.hhplus.ecommerce.domain.product.service.serviceDTO.ProductDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDTO {
    private Long customerId;
    private Long orderNumber;
    private ProductDTO[] productList;
    private String orderState;
    private BigDecimal totalPrice;

    public PaymentDTO(Long customerId, Long orderNumber, ProductDTO[] productList) {
        this.customerId = customerId;
        this.orderNumber = orderNumber;
        this.productList = productList;
    }

    public PaymentDTO(Long customerId, Long orderNumber, String orderState) {
        this.customerId = customerId;
        this.orderNumber = orderNumber;
        this.orderState = orderState;
    }

    public PaymentDTO(Long customerId, ProductDTO[] productList) {
        this.customerId = customerId;
        this.productList = productList;
    }
}
