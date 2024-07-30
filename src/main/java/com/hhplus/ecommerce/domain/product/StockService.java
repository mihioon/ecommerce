package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.domain.NullChecker;
import com.hhplus.ecommerce.infrastructure.redis.RedissonLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import static com.hhplus.ecommerce.support.exception.ErrorCode.STOCK_DEDUCT_FAILED;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {
    private final StockManager stockManager;

    //재고차감
    @Transactional
    @RedissonLock(value = "deductStocks")
    public void deductStocks(List<StockCommand> stocks){
        log.info(Thread.currentThread() + ">> 스레드 시작!");
        NullChecker.checkNotNull(stocks, "List<StockCommand>");

        // ID 순서로 정렬
        stocks.sort(Comparator.comparing(StockCommand::getProductId));

        for(StockCommand stock : stocks){
            stockManager.deductStock(stock);
        }
        log.info(Thread.currentThread() + ">> 스레드 끝!");
    }

    //주문 취소 - 재고추가
    @Transactional
    public void addStocks(List<StockCommand> stocks){
        for(StockCommand stock : stocks){
            stockManager.addStock(stock);
        }
    }
}
