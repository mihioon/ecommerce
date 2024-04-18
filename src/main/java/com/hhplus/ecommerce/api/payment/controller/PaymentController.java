package com.hhplus.ecommerce.api.payment.controller;

import com.hhplus.ecommerce.api.ApiResponse;
import com.hhplus.ecommerce.api.payment.controllerDTO.PaymentCheckOutRequest;
import com.hhplus.ecommerce.api.payment.controllerDTO.PaymentCheckOutResponse;
import com.hhplus.ecommerce.application.payment.CartCheckOutUseCase;
import com.hhplus.ecommerce.business.payment.service.serviceDTO.PaymentDTO;
import com.hhplus.ecommerce.exception.InputValidationException;
import com.hhplus.ecommerce.exception.MapperException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final CartCheckOutUseCase cartCheckOutUseCase;
    private final PaymentDTOmapper mapper;

    public PaymentController(CartCheckOutUseCase cartCheckOutUseCase, PaymentDTOmapper mapper){
        this.cartCheckOutUseCase = cartCheckOutUseCase;
        this.mapper = mapper;
    }

    // 주문 / 결제 api
    @PostMapping("/checkOut")
    public ResponseEntity<ApiResponse<PaymentCheckOutResponse>> customerCartCheckOut(@RequestBody PaymentCheckOutRequest param){
        try {
            PaymentDTO paymentDTO = mapper.requestToPaymentDTO(param);
            paymentDTO = cartCheckOutUseCase.cartCheckOut(paymentDTO);

            return ResponseEntity.ok(new ApiResponse<PaymentCheckOutResponse>("주문 성공", mapper.paymentDtoToResponse(paymentDTO)));
        } catch (InputValidationException|MapperException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage()));
        }
    }

}
