package com.hhplus.ecommerce.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Payment {
    private Long id;
    private Type type;
    private BigDecimal paymentAmount;

    public enum Type { // 결제수단
        // POINT, //포인트
        PAY,
        IAMPORT //PG
    }

    public Payment(String method, BigDecimal paymentAmount) {
        this.type = Payment.Type.valueOf(method);
        this.paymentAmount = paymentAmount;
    }
}
