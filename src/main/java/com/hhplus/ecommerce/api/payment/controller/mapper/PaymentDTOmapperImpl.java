package com.hhplus.ecommerce.api.payment.controller.mapper;

import com.hhplus.ecommerce.api.payment.controllerDTO.PaymentCheckOutRequest;
import com.hhplus.ecommerce.domain.payment.service.serviceDTO.PaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentDTOmapperImpl implements PaymentDTOmapper {
    public PaymentDTO requestToPaymentDTO(PaymentCheckOutRequest param){
        return null;
    }
}
