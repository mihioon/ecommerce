package com.hhplus.ecommerce.domain.user;

import com.hhplus.ecommerce.support.exception.InputValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.hhplus.ecommerce.support.exception.ErrorCode.CHARGE_AMOUNT_IS_NEGATIVE;
import static com.hhplus.ecommerce.support.exception.ErrorCode.NOT_ENOUGH_STOCK;

@Getter
@AllArgsConstructor
public class PointCommand {
    private Long userId;
    private BigDecimal point; /* 금액 */

    // 포인트 유효성 검사
    public void validate() {
        if (point.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InputValidationException(CHARGE_AMOUNT_IS_NEGATIVE.getMessage());
        }
    }
}
