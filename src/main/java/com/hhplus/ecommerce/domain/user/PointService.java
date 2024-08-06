package com.hhplus.ecommerce.domain.user;

import com.hhplus.ecommerce.support.exception.InputValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.hhplus.ecommerce.support.exception.ErrorCode.POINT_CONCURRENCY_FAILURE;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointService {
    //@RequiredArgsConstructor : final 필드 또는 @NonNull 어노테이션이 붙은 필드를 포함한 생성자를 자동으로 생성
    private final UserFinder userFinder;
    private final UserRepository userRepo;


    // 포인트 조회
    public BigDecimal findPoint(Long customerId) {
        return userRepo.findPoint(customerId);
    }

    // 포인트 충전
    @Retryable(
            retryFor = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 1, // 재시도 횟수
            backoff = @Backoff(100) // 재시도 간격
    )
    public void chargePoint(PointCommand pointCommand){
        // 포인트 유효성 검사
        pointCommand.validate();
        // 사용자 금액 업데이트 및 내역 저장
        userRepo.chargePointAndSaveHistory(pointCommand);
    }

    // 포인트 사용
    @Retryable(
            retryFor = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 1, // 재시도 횟수
            backoff = @Backoff(100) // 재시도 간격
    )
    public void usePoint(PointCommand pointCommand){
        // 유저 검색
        User user = userFinder.find(pointCommand.getUserId());
        // 포인트 사용 유효성 검사
        user.validPoint(pointCommand.getPoint());
        // 사용자 금액 업데이트 및 내역 저장
        userRepo.usePointAndSaveHistory(pointCommand);
    }

    @Recover
    public void recover(ObjectOptimisticLockingFailureException e) {
        // 포인트 로직 실패 에러
        throw new InputValidationException(POINT_CONCURRENCY_FAILURE.getMessage());
    }

}
