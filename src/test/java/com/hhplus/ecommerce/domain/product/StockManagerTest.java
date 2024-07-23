package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.support.exception.InputValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockManagerTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private StockManager stockManager;

    @Test
    @DisplayName("StockCommand is null 예외 확인")
    public void test_stock_command_not_found_exception() {
        // then
        assertThrows(
                NullPointerException.class,
                () -> stockManager.deductStock(null)
        );
    }

    @Test
    @DisplayName("productId is null 예외 확인")
    public void test_product_id_not_found_exception() {
        // given
        StockCommand stock = new StockCommand(null, null);

        // then
        assertThrows(
                NullPointerException.class,
                () -> stockManager.deductStock(stock)
        );
    }

    @Test
    @DisplayName("재고검증-주문수량 오류 null 예외 확인")
    public void test_stock_validate_exception_1() {
        // given
        StockCommand stock = new StockCommand(1L, null);

        // when
        when(productRepository.findStockById(1L)).thenReturn(1L);

        // then
        assertThrows(
                IllegalArgumentException.class,
                () -> stockManager.deductStock(stock)
        );
    }

    @Test
    @DisplayName("재고검증-주문수량 오류 0 예외 확인")
    public void test_stock_validate_exception_2() {
        // given
        StockCommand stock = new StockCommand(1L, 0L);

        // when
        when(productRepository.findStockById(1L)).thenReturn(1L);

        // then
        assertThrows(
                IllegalArgumentException.class,
                () -> stockManager.deductStock(stock)
        );
    }

    @Test
    @DisplayName("재고검증-재고수량 오류 null 예외 확인")
    public void test_stock_validate_exception_3() {
        // given
        StockCommand stock = new StockCommand(1L, 1L);

        // when
        when(productRepository.findStockById(1L)).thenReturn(null);

        // then
        assertThrows(
                NullPointerException.class,
                () -> stockManager.deductStock(stock)
        );
    }

    @Test
    @DisplayName("재고검증-재고수량 오류 재고수량부족 예외 확인")
    public void test_stock_validate_exception_4() {
        // given
        StockCommand stock = new StockCommand(1L, 1L);

        // when
        when(productRepository.findStockById(1L)).thenReturn(0L);

        // then
        assertThrows(
                InputValidationException.class,
                () -> stockManager.deductStock(stock)
        );
    }

    @Test
    @DisplayName("재고 감소 확인")
    public void test_deduct_stock() {
        // given
        StockCommand stock = new StockCommand(1L, 1L);

        // when
        when(productRepository.findStockById(1L)).thenReturn(1L);
        stockManager.deductStock(stock);

        // then
        verify(productRepository).findStockById(1L); // 호출 검증
        verify(productRepository).updateQuantity(1L, 0L); // 수량 감소 후 저장하는지 체크
    }

    @Test
    @DisplayName("재고 증가 확인")
    public void test_add_stock() {
        // given
        StockCommand stock = new StockCommand(1L, 1L);

        // when - 주문수량이 현재수량보다 많아도 통과하는지 확인
        when(productRepository.findStockById(1L)).thenReturn(0L);
        stockManager.addStock(stock);

        // then
        verify(productRepository).findStockById(1L); // 호출 검증
        verify(productRepository).updateQuantity(1L, 1L); // 수량 증가 후 저장하는지 체크
    }

}