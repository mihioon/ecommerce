package com.hhplus.ecommerce.domain.customer;

import com.hhplus.ecommerce.util.PointType;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class PointHistory {
    private Long customerId;
    private LocalDateTime dateTime;
    private PointType type;
    private BigDecimal point; /* 금액 */

    public PointHistory(Long customerId, LocalDateTime dateTime, PointType type, BigDecimal point) throws IllegalArgumentException {
        // 포인트 유효성 검사
        if (point.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("point는 0보다 큰 값이어야 합니다.");
        }

        this.customerId = customerId;
        this.dateTime = dateTime;
        this.type = type;
        this.point = point;
    }
}
