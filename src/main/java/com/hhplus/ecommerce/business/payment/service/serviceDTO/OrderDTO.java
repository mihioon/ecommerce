package com.hhplus.ecommerce.business.payment.service.serviceDTO;

import com.hhplus.ecommerce.business.product.service.serviceDTO.ProductDTO;
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
