package com.hhplus.ecommerce.api.controller;

import com.hhplus.ecommerce.api.ApiResponse;
import com.hhplus.ecommerce.api.controllerDTO.request.CustomerAmountRequest;
import com.hhplus.ecommerce.api.controllerDTO.response.CustomerAmountResponse;
import com.hhplus.ecommerce.application.customer.ChargeCustomerAmountUseCase;
import com.hhplus.ecommerce.domain.customer.service.serviceDTO.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private final ChargeCustomerAmountUseCase chargeCustomerAmountUseCase;
    private final CustomerDTOmapper mapper;

    public CustomerController(ChargeCustomerAmountUseCase chargeCustomerAmountUseCase, CustomerDTOmapper mapper) {
        this.chargeCustomerAmountUseCase = chargeCustomerAmountUseCase;
        this.mapper = mapper;
    }

    // 잔액충전 / 조회 api
    @PostMapping("/customer/amount")
    public ResponseEntity<ApiResponse<CustomerAmountResponse>> chargeCustomerAmount(@RequestBody CustomerAmountRequest param) {
        try {
            CustomerDTO customerDTO = mapper.requestToCustomerDto(param);
            customerDTO =  chargeCustomerAmountUseCase.chargeCustomerAmount(customerDTO);
            return ResponseEntity.ok(new ApiResponse<CustomerAmountResponse>("잔액 저장 성공",mapper.customerDtoToResponse(customerDTO)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage()));
        }
    }
}
