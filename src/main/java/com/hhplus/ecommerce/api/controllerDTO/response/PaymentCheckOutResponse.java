package com.hhplus.ecommerce.api.controllerDTO.response;

import com.hhplus.ecommerce.domain.order.object.OrderHistory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentCheckOutResponse {
    private OrderHistory orderHistory;
}
