package com.hhplus.ecommerce.domain.customer.object;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PointHistory {
    private Long customerId;
    private LocalDateTime dateTime;
    private PointType type;
    private BigDecimal point; /* 금액 */
}
