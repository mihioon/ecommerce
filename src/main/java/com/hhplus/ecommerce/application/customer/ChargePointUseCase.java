package com.hhplus.ecommerce.application.customer;

import com.hhplus.ecommerce.domain.customer.Customer;
import com.hhplus.ecommerce.domain.customer.PointHistory;
import com.hhplus.ecommerce.domain.customer.CustomerService;
import com.hhplus.ecommerce.domain.customer.PointHistoryService;
import com.hhplus.ecommerce.util.PointType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Spring Data JPA에서 낙관적락 사용시
 * 버전 충돌 시 Hibernate는 StaleStateException을 발생시킴
 * Spring은 이를 OptimisticLockingFailureException으로 래핑하여 처리
 * */
@Component
@RequiredArgsConstructor
public class ChargePointUseCase {
    private final CustomerService customerService;
    private final PointHistoryService pointHistoryService;

    @Transactional
    @Retryable(
            retryFor = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 3,
            backoff = @Backoff(100)
    )
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

    @Recover
    public void recover(ObjectOptimisticLockingFailureException e, Long customerId, BigDecimal amount) {
        // 포인트 충전 실패 로직
        System.out.println("체크용");
    }
}
