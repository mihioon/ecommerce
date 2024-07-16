package com.hhplus.ecommerce.api.controller;

import com.hhplus.ecommerce.api.ApiResponse;
import com.hhplus.ecommerce.api.controllerDTO.request.OrderRequest;
import com.hhplus.ecommerce.api.controllerDTO.response.PaymentCheckOutResponse;
import com.hhplus.ecommerce.application.payment.CheckOutUseCase;
import com.hhplus.ecommerce.domain.order.OrderHistory;
import com.hhplus.ecommerce.domain.order.OrderProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final CheckOutUseCase checkOutUseCase;

    // 주문 / 결제 api
    @PostMapping("/{customerId}/{totalPrice}")
    public ResponseEntity<ApiResponse<PaymentCheckOutResponse>> customerCartCheckOut(@PathVariable("customerId") Long customerId, @PathVariable("totalPrice") BigDecimal totalPrice, @RequestBody OrderRequest requestParam){
        List<OrderProduct> orderProducts = requestParam.getProductList().toDomain();
        OrderHistory result = checkOutUseCase.checkOut(customerId, totalPrice, orderProducts);
        return ResponseEntity.ok(new ApiResponse<PaymentCheckOutResponse>("주문 성공", new PaymentCheckOutResponse(result)));
    }
}
