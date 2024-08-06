package com.hhplus.ecommerce.api.controller;

import com.hhplus.ecommerce.api.ApiResponse;
import com.hhplus.ecommerce.api.SuccessType;
import com.hhplus.ecommerce.api.controllerDTO.request.OrderSheetRequest;
import com.hhplus.ecommerce.domain.order.OrderSheet;
import com.hhplus.ecommerce.domain.order.OrderSheetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orderSheet")
public class OrderSheetController {
    OrderSheetService orderSheetService;

    // 주문서 저장
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Long>> createOrderSheet(@RequestParam OrderSheetRequest OrderSheetRequest) {
        OrderSheet orderSheet = OrderSheetRequest.toOrderSheet();
        Long orderSheetId = orderSheetService.create(orderSheet);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.msg, orderSheetId));
    }

    // 주문서 조회
    @GetMapping("/{orderSheetId}")
    public ResponseEntity<ApiResponse<OrderSheet>> findOrderSheet(@PathVariable Long orderSheetId) {
        OrderSheet orderSheet = orderSheetService.find(orderSheetId);
        return ResponseEntity.ok(ApiResponse.success(SuccessType.msg, orderSheet));
    }
}
