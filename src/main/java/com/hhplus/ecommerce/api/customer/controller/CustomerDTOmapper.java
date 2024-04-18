package com.hhplus.ecommerce.api.customer.controller;

import com.hhplus.ecommerce.api.customer.controllerDTO.CustomerAmountRequest;
import com.hhplus.ecommerce.api.customer.controllerDTO.CustomerAmountResponse;
import com.hhplus.ecommerce.business.customer.service.serviceDTO.CustomerDTO;
import com.hhplus.ecommerce.exception.ConvertNumber;
import com.hhplus.ecommerce.exception.MapperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CustomerDTOmapper {
    private final ConvertNumber convertNumber;
    public CustomerDTOmapper(ConvertNumber convertNumber){
        this.convertNumber = convertNumber;
    }

    public CustomerDTO requestToCustomerDto(CustomerAmountRequest param) {
        if(param == null){
            throw new MapperException("입력 값이 없습니다.");
        }
        Long customerId = convertNumber.convertLong(param.customerId);
        BigDecimal chargeAmount = convertNumber.convertBigDecimal(param.chargeAmount);

        CustomerDTO result = new CustomerDTO(customerId, "CHARGE", chargeAmount);

        return result;
    }

    public CustomerAmountResponse customerDtoToResponse(CustomerDTO param) {
        return new CustomerAmountResponse(param.getCustomerId(), param.getType(), param.getAmount());
    }
}
