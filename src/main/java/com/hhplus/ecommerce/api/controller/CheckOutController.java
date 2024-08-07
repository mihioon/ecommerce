package com.hhplus.ecommerce.api.controller;

import com.hhplus.ecommerce.api.controllerDTO.request.CheckOutRequest;
import com.hhplus.ecommerce.application.CheckOutProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckOutController {
    private final CheckOutProvider checkOutUseCase;

    // 주문 / 결제 api
    @PostMapping("/order")
    public ResponseEntity<Void> checkOut(@RequestBody CheckOutRequest checkOutRequest){
        checkOutUseCase.checkOut(checkOutRequest);
        return ResponseEntity.ok().build();
    }
}
