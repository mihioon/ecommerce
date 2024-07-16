package com.hhplus.ecommerce.api.controller;

import com.hhplus.ecommerce.api.ApiResponse;
import com.hhplus.ecommerce.api.controllerDTO.request.CustomerPointRequest;
import com.hhplus.ecommerce.api.controllerDTO.response.CustomerAmountResponse;
import com.hhplus.ecommerce.application.customer.ChargePointUseCase;
import com.hhplus.ecommerce.domain.customer.PointHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final ChargePointUseCase chargePointUseCase;

    // 잔액충전 / 조회 api
    @PostMapping("/customer/amount")
    public ResponseEntity<ApiResponse<CustomerAmountResponse>> chargeCustomerAmount(@RequestBody CustomerPointRequest requestParam) {
        List<PointHistory> result = chargePointUseCase.chargePoint(requestParam.getCustomerId(), requestParam.getPoint());
        return ResponseEntity.ok(new ApiResponse<CustomerAmountResponse>("잔액 저장 성공", new CustomerAmountResponse(result)));
    }
}
