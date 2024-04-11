package com.hhplus.ecommerce.api.customer.controller;

import com.hhplus.ecommerce.api.customer.controller.mapper.CustomerDTOmapper;
import com.hhplus.ecommerce.api.customer.controllerDTO.CustomerAmountRequest;
import com.hhplus.ecommerce.api.customer.controllerDTO.CustomerAmountResponse;
import com.hhplus.ecommerce.domain.customer.service.CustomerService;
import com.hhplus.ecommerce.domain.customer.service.serviceDTO.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerDTOmapper mapper;

    // 잔액충전 / 조회 api
    @PostMapping("/customer/amount")
    public ResponseEntity<?> chargeCustomerAmount(@RequestBody CustomerAmountRequest param) {
        try {
            CustomerDTO customerDTO = mapper.requestToCustomerDTO(param);
            customerDTO.setType("CHARGE");

            CustomerAmountResponse result = CustomerAmountResponse.builder()
                    .code("SUCCESS")
                    .message("충전되었습니다.")
                    .body(customerService.saveCustomerAmount(customerDTO))
                    .build();
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e){
            CustomerAmountResponse errorResult = CustomerAmountResponse.builder()
                    .code("ERROR")
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorResult);
        }
    }
}
