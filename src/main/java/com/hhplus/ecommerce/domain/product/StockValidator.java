package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.domain.NullChecker;
import com.hhplus.ecommerce.support.exception.InputValidationException;
import org.springframework.stereotype.Component;

import static com.hhplus.ecommerce.support.exception.ErrorCode.NOT_ENOUGH_STOCK;

@Component
public class StockValidator {
    public void validateStock(Product.Detail productDetail, Long orderQuantity, Long productId){
        // productDetail null check
        NullChecker.checkNotNull(productDetail, "ProductDetail");

        // 현재 재고
        Long currentQuantity = productDetail.getQuantity();

        // currentQuantity null check
        NullChecker.checkNotNull(currentQuantity, "Quantity");

        // 재고 수량 체크
        if (currentQuantity < orderQuantity) {
            throw new InputValidationException(NOT_ENOUGH_STOCK.getMessage());
        }
    }
}
