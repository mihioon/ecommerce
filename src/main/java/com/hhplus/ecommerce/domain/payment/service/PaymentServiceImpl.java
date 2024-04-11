package com.hhplus.ecommerce.domain.payment.service;

import com.hhplus.ecommerce.domain.payment.service.serviceDTO.PaymentDTO;
import com.hhplus.ecommerce.domain.product.service.serviceDTO.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Override
    public List<ProductDTO> cartCheckOut(PaymentDTO param) {
        return null;
    }
}
