package com.hhplus.ecommerce.api.customer.controller.mapper;

import com.hhplus.ecommerce.api.customer.controllerDTO.CustomerAmountRequest;
import com.hhplus.ecommerce.domain.customer.service.serviceDTO.CustomerDTO;
import com.hhplus.ecommerce.exception.ConvertNumber;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public interface CustomerDTOmapper {
    public CustomerDTO requestToCustomerDTO(CustomerAmountRequest param);
}
