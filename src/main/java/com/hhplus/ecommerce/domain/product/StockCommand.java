package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.domain.NullChecker;
import com.hhplus.ecommerce.support.exception.InputValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

import static com.hhplus.ecommerce.support.exception.ErrorCode.NOT_ENOUGH_STOCK;
import static com.hhplus.ecommerce.support.exception.ErrorCode.ORDER_QUANTITY_IS_NOT_POSITIVE;

@Getter
@AllArgsConstructor
public class StockCommand {
    private Long productId;
    private Long orderQuantity;

    // 재고 검증
    public void validateValue(Long currentQuantity){
        // 주문서에서 검증된 값이 내부서버에서 오류
        Optional.ofNullable(orderQuantity)
                .filter(qty -> qty > 0)
                .orElseThrow(() -> new IllegalArgumentException(ORDER_QUANTITY_IS_NOT_POSITIVE.getMessage()));

        // 재고가 조회되지 않아 null 인 경우
        NullChecker.checkNotNull(currentQuantity, "currentQuantity");
    }

    public void validateForDeduction(Long currentQuantity){
        if (currentQuantity < orderQuantity) {
            throw new InputValidationException(NOT_ENOUGH_STOCK.getMessage());
        }
    }
}
