package com.hhplus.ecommerce.api.payment.controllerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentProductRequest {
    private String productId;
    private String quantity;
    private String salePrice;
}
