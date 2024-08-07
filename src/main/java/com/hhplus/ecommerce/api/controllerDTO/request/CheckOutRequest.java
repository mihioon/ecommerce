package com.hhplus.ecommerce.api.controllerDTO.request;

import com.hhplus.ecommerce.domain.payment.Payment;
import com.hhplus.ecommerce.domain.user.PointCommand;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CheckOutRequest {
    private Long orderSheetId;
    private Long userId;
    private Long orderId;
    private BigDecimal orderPrice; // 총 주문 금액
    private String payType; // 결제 타입
    private BigDecimal point;

    public PointCommand toPointCommand() {
        return new PointCommand(userId, orderPrice);
    }

    public Payment toPayment() {
        return new Payment(
                null,
                Payment.Type.valueOf(payType.toUpperCase()),
                orderPrice
        );
    }
}
