package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.support.exception.InputValidationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class StockServiceTest {
    @Autowired
    StockService stockService;

    @Autowired
    ProductService productService;

    // 전역변수
    Long productId;
    Long productId2;
    Long catId;
    List<StockCommand> stocks = new ArrayList<>();

    @BeforeEach
    void setUp() { // 상품정보 저장
        Product.Detail productDetail = new Product.Detail(
                null, 2L, 0L, 0L
        );
        Product product = new Product(
                null, "강아지 인형", new BigDecimal("100"), productDetail
        );
        productId = productService.saveProduct(product);

        productDetail = new Product.Detail(
                null, 3L, 0L, 0L
        );
        product = new Product(
                null, "곰돌이 인형", new BigDecimal("200"), productDetail
        );
        productId2 = productService.saveProduct(product);

        // concurrency
        productDetail = new Product.Detail(
                null, 0L, 0L, 0L
        );
        product = new Product(
                null, "고양이 인형", new BigDecimal("100"), productDetail
        );
        catId = productService.saveProduct(product);
    }

    @Test
    @DisplayName("상품 1개 주문 테스트")
    public void test_deduct_stock() {
        // given
        // when
        stocks.add(new StockCommand(productId, 1L)); // 강아지 인형 1개 주문
        stockService.deductStocks(stocks);

        // then
        Product result = productService.findProduct(productId);
        assertThat(result.getId()).isEqualTo(productId);
        assertThat(result.getProductDetail().getQuantity()).isEqualTo(1L);
    }

    @Test
    @DisplayName("상품 2개 주문 테스트")
    public void test_deduct_stocks() {
        // given
        // when
        stocks.add(new StockCommand(productId, 1L)); // 강아지 인형 1개 주문
        stocks.add(new StockCommand(productId2, 1L)); // 곰돌이 인형 1개 주문
        stockService.deductStocks(stocks);

        // then
        Product result = productService.findProduct(productId);
        assertThat(result.getId()).isEqualTo(productId);
        assertThat(result.getProductDetail().getQuantity()).isEqualTo(1L);

        result = productService.findProduct(productId2);
        assertThat(result.getId()).isEqualTo(productId2);
        assertThat(result.getProductDetail().getQuantity()).isEqualTo(2L);
    }

    @Test
    @DisplayName("상품 주문 동시성 테스트")
    public void test_concurrency_deduct_stocks() throws InterruptedException {
        // given - 고양이 인형 0 -> 5개 추가
        stocks.add(new StockCommand(catId, 5L));
        stockService.addStocks(stocks);
        // given - 주문 객체 세팅
        stocks = new ArrayList<>(); // 초기화
        stocks.add(new StockCommand(catId, 1L));

        // when
        int numThreads = 5; //동시에 실행할 스레드 수
        CountDownLatch doneSignal = new CountDownLatch(numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        Long start =  System.currentTimeMillis();
        log.info(">>>>>> 작업 시작 >>>>>>");

        for (int i = 0; i < numThreads; i++) {
            executorService.execute(() -> {
                try {
                    stockService.deductStocks(stocks);
                    successCount.getAndIncrement();
                } catch (InputValidationException e) {
                    failCount.getAndIncrement();
                } finally {
                    doneSignal.countDown();
                }
            });
        }

        doneSignal.await(); //스레드 작업이 완료될 때 까지 대기
        executorService.shutdown(); //작업이 완료된 후 스레드 풀 종료

        Long end =  System.currentTimeMillis();
        log.info(">>>>>> 작업 완료 >>>>>>");

        log.info("총 작업 시간: " + (end - start) + "ms");
        // then
        assertAll(
                () -> assertThat(successCount.get()).isEqualTo(5),
                () -> assertThat(failCount.get()).isEqualTo(0)
        );

        Product result = productService.findProduct(catId);
        assertThat(result.getId()).isEqualTo(catId);
        assertThat(result.getProductDetail().getQuantity()).isEqualTo( 5L - ((long) successCount.get()*1L));
    }
}