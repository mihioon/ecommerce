package com.hhplus.ecommerce.domain.customer.object;

import com.hhplus.ecommerce.domain.payment.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class Customer {
    private Long id;
    private BigDecimal point;
}
