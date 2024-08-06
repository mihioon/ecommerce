package com.hhplus.ecommerce.api.controllerDTO.request;

import com.hhplus.ecommerce.domain.order.OrderProduct;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class OrderPaymentsRequest {
    private List<OrderPaymentRequest>  list;

    @Getter
    static class OrderPaymentRequest{
        private String type; // 주문 타입
        private BigDecimal price; // 결제 금액
    }
}
