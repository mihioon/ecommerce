package com.hhplus.ecommerce.application.customer;

import com.hhplus.ecommerce.domain.customer.object.Customer;
import com.hhplus.ecommerce.domain.customer.object.PointHistory;
import com.hhplus.ecommerce.domain.customer.service.CustomerService;
import com.hhplus.ecommerce.domain.customer.service.PointHistoryService;
import com.hhplus.ecommerce.util.PointType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ChargePointUseCase {
    private final CustomerService customerService;
    private final PointHistoryService pointHistoryService;

    @Transactional
    public List<PointHistory> chargePoint(Long customerId, BigDecimal point){
        // 사용자 여부 확인
        Customer customer = customerService.findCustomer(customerId);

        // 충전 내역 저장
        LocalDateTime dateTime = LocalDateTime.now();
        PointHistory pointHistory = new PointHistory(customerId, dateTime, PointType.CHARGE, point);
        pointHistoryService.savePointHistory(pointHistory);

        // 사용자 금액 업데이트
        customer.chargePoint(point);
        customerService.updateChargeCustomer(customer);

        // 사용자 포인트 내역 조회
        return pointHistoryService.findHistoryByCustomerId(customerId);
    }
}
