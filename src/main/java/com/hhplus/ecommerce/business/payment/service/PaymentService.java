package com.hhplus.ecommerce.business.payment.service;

import com.hhplus.ecommerce.business.payment.service.serviceDTO.PaymentDTO;
import com.hhplus.ecommerce.business.product.service.serviceDTO.ProductDTO;

import java.util.List;

public interface PaymentService {
    public List<ProductDTO> cartCheckOut(PaymentDTO param);
}
