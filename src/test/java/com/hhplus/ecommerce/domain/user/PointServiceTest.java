package com.hhplus.ecommerce.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class PointServiceTest {
    @Autowired
    PointService pointService;
    @Autowired
    UserService userService;

    Long userId;

    @BeforeEach
    void setUp() {
        userId = userService.save(new User(null, BigDecimal.ZERO)).getId(); // Customer 초기화
    }

    @Test
    @DisplayName("사용자 금액 충전 확인")
    void charge_point_service_test() {
        // given
        BigDecimal requestPoint = BigDecimal.valueOf(1000);
        PointCommand pointCommand = new PointCommand(userId, requestPoint);

        // when
        pointService.chargePoint(pointCommand);
        pointService.chargePoint(pointCommand);

        // then
        User user = userService.findUser(userId);
        assertThat(user.getId()).isEqualTo(userId);
        assertEquals(0, (requestPoint).multiply(BigDecimal.valueOf(2)).compareTo(user.getPoint()));
    }

    @Test
    @DisplayName("사용자 금액 사용 확인")
    void use_point_service_test() {
        // given
        BigDecimal requestPoint = BigDecimal.valueOf(2000);
        pointService.chargePoint(new PointCommand(userId, requestPoint));

        // when
        BigDecimal usePoint = BigDecimal.valueOf(1000);
        pointService.usePoint(new PointCommand(userId, usePoint));

        // then
        User user = userService.findUser(userId);
        assertThat(user.getId()).isEqualTo(userId);
        assertEquals(0, (requestPoint.subtract(usePoint)).compareTo(user.getPoint()));
    }

    @Test
    @DisplayName("사용자 point 충전 동시성 처리")
    void concurrency_point_check() throws InterruptedException {
        // given
        int numThreads = 2; //동시에 실행할 스레드 수
        BigDecimal requestPoint = BigDecimal.valueOf(1000);
        PointCommand pointCommand = new PointCommand(userId, requestPoint);

        // when
        CountDownLatch doneSignal = new CountDownLatch(numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        for (int i = 0; i < numThreads; i++) { //각 스레드에 작업 할당
            executorService.execute(() -> { // 스레드 풀 생성
                try{
                    pointService.chargePoint(pointCommand);
                    successCount.getAndIncrement();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    failCount.getAndIncrement();
                }finally {
                    doneSignal.countDown(); //작업이 완료될 때 마다 카운트 감소
                }
            });
        }
        doneSignal.await(); //스레드 작업이 완료될 때 까지 대기
        executorService.shutdown(); //작업이 완료된 후 스레드 풀 종료

        // then
        User user = userService.findUser(userId);
        assertThat(user.getId()).isEqualTo(userId);

        assertEquals(0, user.getPoint().compareTo(requestPoint.multiply(BigDecimal.valueOf(successCount.get()))));
    }
}