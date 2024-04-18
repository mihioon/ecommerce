package com.hhplus.ecommerce.api.payment.controllerDTO;

import com.hhplus.ecommerce.business.payment.service.serviceDTO.PaymentDTO;
import com.hhplus.ecommerce.business.product.service.serviceDTO.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
public class PaymentCheckOutResponse {
    private Long orderNumber;
    private PaymentProductResponse[] productList;
}
