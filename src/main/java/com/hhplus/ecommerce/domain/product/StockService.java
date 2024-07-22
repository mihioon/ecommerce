package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.domain.NullChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockDecrementer stockDecrementer;
    private final StockIncrementer stockIncrementer;
    private final NullChecker nullChecker;

    //재고차감
    @Transactional
    public void deductStocks(List<StockCommand> stocks){
        NullChecker.checkNotNull(stocks, "List<StockCommand>");
        for(StockCommand stock : stocks){
            stockDecrementer.deductStock(stock);
        }
    }

    //주문 취소 - 재고추가
    @Transactional
    public void addStocks(List<StockCommand> stocks){
        for(StockCommand stock : stocks){
            stockIncrementer.addStock(stock);
        }
    }
}
