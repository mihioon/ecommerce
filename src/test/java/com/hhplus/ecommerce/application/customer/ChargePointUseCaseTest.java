package com.hhplus.ecommerce.application.customer;

import com.hhplus.ecommerce.domain.customer.Customer;
import com.hhplus.ecommerce.domain.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChargePointUseCaseTest {
    @Autowired
    ChargePointUseCase chargePointUseCase;
    @Autowired
    CustomerService customerService;

    Long customerId;

    @BeforeEach
    void setUp() {
        Customer customer2 = customerService.save(new Customer(null, BigDecimal.ZERO)); // Customer 초기화
        customerId = customer2.getId();
    }

    @Test
    @DisplayName("사용자 금액 충전 확인")
    void chargePointUseCaseTest() {
        BigDecimal requestPoint = BigDecimal.valueOf(100);
        chargePointUseCase.chargePoint(customerId, requestPoint);
        chargePointUseCase.chargePoint(customerId, requestPoint);

        Customer customer = customerService.findCustomer(customerId);
        assertThat(customer.getId()).isEqualTo(customerId);
        assertThat(customer.getPoint().setScale(0, BigDecimal.ROUND_DOWN)).isEqualTo(BigDecimal.valueOf(2).multiply(requestPoint));
        assertEquals(0, (requestPoint).multiply(BigDecimal.valueOf(2)).compareTo(customer.getPoint()));
    }

    @Test
    @DisplayName("사용자 point 충전 동시성 처리-스레드 수가 retry 가능한 횟수 이내일 경우")
    void concurrencyOrderThreadsOk() throws InterruptedException {
        int numThreads = 100; //동시에 실행할 스레드 수
        BigDecimal requestPoint = BigDecimal.valueOf(100);
        CountDownLatch doneSignal = new CountDownLatch(numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) { //각 스레드에 작업 할당
            executorService.execute(() -> { // 스레드 풀 생성
                try{
                    chargePointUseCase.chargePoint(customerId, requestPoint);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                doneSignal.countDown(); //작업이 완료될 때 마다 카운트 감소
            });
        }
        doneSignal.await(); //스레드 작업이 완료될 때 까지 대기
        executorService.shutdown(); //작업이 완료된 후 스레드 풀 종료

        Customer customer = customerService.findCustomer(customerId);
        assertThat(customer.getId()).isEqualTo(customerId);
        //assertEquals(0, customer.getPoint().compareTo(BigDecimal.valueOf(numThreads).multiply(requestPoint)));
        assertThat(customer.getPoint().setScale(0, BigDecimal.ROUND_DOWN)).isEqualTo(BigDecimal.valueOf(numThreads).multiply(requestPoint));
        //assertThat(customer.getPoint().stripTrailingZeros()).isEqualTo(BigDecimal.valueOf(numThreads).multiply(requestPoint).stripTrailingZeros());
    }

}