package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.domain.NullChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {
    private final StockManager stockManager;

    //재고차감
    @Transactional
    public void deductStocks(List<StockCommand> stocks){
        log.info(Thread.currentThread() + ">> 스레드 시작!");
        NullChecker.checkNotNull(stocks, "List<StockCommand>");
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
