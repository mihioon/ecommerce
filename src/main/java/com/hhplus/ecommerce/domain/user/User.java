package com.hhplus.ecommerce.domain.user;

import com.hhplus.ecommerce.support.exception.InputValidationException;
import lombok.Getter;

import java.math.BigDecimal;

import static com.hhplus.ecommerce.support.exception.ErrorCode.NOT_ENOUGH_BALANCE;
import static com.hhplus.ecommerce.support.exception.ErrorCode.ORDER_QUANTITY_IS_NOT_POSITIVE;

@Getter
public class User {
    private Long id;
    private BigDecimal point;

    public User(Long id, BigDecimal point) throws IllegalArgumentException {
        this.id = id;
        this.point = point;
    }

    public void validPoint(BigDecimal pointToUse){
        if (this.point.compareTo(pointToUse) < 0) {
            throw new InputValidationException(NOT_ENOUGH_BALANCE.getMessage());
        }
    }
}
