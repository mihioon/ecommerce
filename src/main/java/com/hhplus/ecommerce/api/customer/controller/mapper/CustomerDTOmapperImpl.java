package com.hhplus.ecommerce.api.customer.controller.mapper;

import com.hhplus.ecommerce.api.customer.controllerDTO.CustomerAmountRequest;
import com.hhplus.ecommerce.domain.customer.service.serviceDTO.CustomerDTO;
import com.hhplus.ecommerce.exception.ConvertNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CustomerDTOmapperImpl implements CustomerDTOmapper {
    @Autowired
    ConvertNumber convertNumber;

    public CustomerDTO requestToCustomerDTO(CustomerAmountRequest param) {
        if(param == null){
            throw new IllegalArgumentException("입력 값이 없습니다.");
        }
        Long customerId = convertNumber.convertLong(param.customerId);
        BigDecimal chargeAmount = convertNumber.convertBigDecimal(param.chargeAmount);


        CustomerDTO result = CustomerDTO.builder()
                .type("CHARGE")
                .customerId(customerId)
                .chargeAmount(chargeAmount)
                .build();

        return result;
    }
}
