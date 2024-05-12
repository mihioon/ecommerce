package com.hhplus.ecommerce.api.controllerDTO.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentCheckOutResponse {
    private Long orderNumber;
    private PaymentProductResponse[] productList;
}
