package com.hhplus.ecommerce.api.payment.controller.mapper;

import com.hhplus.ecommerce.api.payment.controllerDTO.PaymentCheckOutRequest;
import com.hhplus.ecommerce.domain.payment.service.serviceDTO.PaymentDTO;

import java.math.BigDecimal;

public interface PaymentDTOmapper {
    public PaymentDTO requestToPaymentDTO(PaymentCheckOutRequest param);
}
