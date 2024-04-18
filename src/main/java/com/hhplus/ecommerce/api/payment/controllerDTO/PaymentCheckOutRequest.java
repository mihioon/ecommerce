package com.hhplus.ecommerce.api.payment.controllerDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class PaymentCheckOutRequest {
    public String customerId;
    public PaymentProductRequest[] productList;
}
