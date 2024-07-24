package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.domain.NullChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import static com.hhplus.ecommerce.support.exception.ErrorCode.STOCK_DEDUCT_FAILED;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {
    private final StockManager stockManager;

    //재고차감
    @Retryable(
            retryFor = {ObjectOptimisticLockingFailureException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 500, maxDelay = 600, random = true)// 밀리초 : 1초
    )
    @Transactional
    public void deductStocks(List<StockCommand> stocks){
        log.info(Thread.currentThread() + ">> 스레드 시작!");
        NullChecker.checkNotNull(stocks, "List<StockCommand>");
        for(StockCommand stock : stocks){
            stockManager.deductStock(stock);
        }
        log.info(Thread.currentThread() + ">> 스레드 끝!");
    }
    @Recover
    public void recover(ObjectOptimisticLockingFailureException e) {
        // 실패시 로직
        throw new IllegalArgumentException(STOCK_DEDUCT_FAILED.getMessage());
    }

    //주문 취소 - 재고추가
    @Transactional
    public void addStocks(List<StockCommand> stocks){
        for(StockCommand stock : stocks){
            stockManager.addStock(stock);
        }
    }
}
