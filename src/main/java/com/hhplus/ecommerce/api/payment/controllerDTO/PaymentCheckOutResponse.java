package com.hhplus.ecommerce.api.payment.controllerDTO;

import com.hhplus.ecommerce.domain.customer.service.serviceDTO.CustomerDTO;
import com.hhplus.ecommerce.domain.payment.service.serviceDTO.PaymentDTO;
import com.hhplus.ecommerce.domain.product.service.serviceDTO.ProductDTO;
import lombok.Builder;

import java.util.List;

@Builder
public class PaymentCheckOutResponse {
    private String code;
    private String message;
    private List<ProductDTO> body;
}
