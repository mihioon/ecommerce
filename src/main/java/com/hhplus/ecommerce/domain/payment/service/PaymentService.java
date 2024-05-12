package com.hhplus.ecommerce.domain.payment.service;

import com.hhplus.ecommerce.domain.payment.service.serviceDTO.PaymentDTO;
import com.hhplus.ecommerce.domain.product.service.serviceDTO.ProductDTO;

import java.util.List;

public interface PaymentService {
    public List<ProductDTO> cartCheckOut(PaymentDTO param);
}
