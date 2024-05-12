package com.hhplus.ecommerce.domain.payment.service.serviceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private String orderState;
    private BigDecimal totalPrice;
    private Date orderDate;
}
