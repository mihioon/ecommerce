package com.hhplus.ecommerce.api.controllerDTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentProductRequest {
    private String productId;
    private String quantity;
    private String salePrice;
}
