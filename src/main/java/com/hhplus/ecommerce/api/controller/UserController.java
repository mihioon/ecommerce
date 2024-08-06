package com.hhplus.ecommerce.api.controller;

import com.hhplus.ecommerce.api.ApiResponse;
import com.hhplus.ecommerce.api.SuccessType;
import com.hhplus.ecommerce.api.controllerDTO.request.PointChargeRequest;
import com.hhplus.ecommerce.domain.user.PointCommand;
import com.hhplus.ecommerce.domain.user.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final PointService pointService;
    // 포인트충전 api
    @PostMapping("/charge")
    public ResponseEntity<Void>  chargePoint(@RequestBody PointChargeRequest requestParam) {
        PointCommand pointCommand = new PointCommand(requestParam.getCustomerId(), requestParam.getPoint());
        pointService.chargePoint(pointCommand);
        return ResponseEntity.ok().build();
    }

    // 포인트조회 api
    @GetMapping("/point/{id}")
    public ResponseEntity<ApiResponse<BigDecimal>> getPoint(@RequestBody PointChargeRequest requestParam) {
        BigDecimal point = pointService.findPoint(requestParam.getCustomerId());
        return ResponseEntity.ok(ApiResponse.success(SuccessType.msg, point));
    }
}
