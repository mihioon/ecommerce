package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.domain.order.OrderReader;
import com.hhplus.ecommerce.domain.order.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductReaderTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductReader productReader;

    @Test
    @DisplayName("상품이 없는 경우 예외 확인")
    public void test_product_read_not_found_exception() {
        // when
        when(productRepository.findProductById(anyLong())).thenReturn(null);

        // then
        assertThrows(
                NullPointerException.class,
                () -> productReader.read(1L)
        );
    }

    @Test
    @DisplayName("상품상세가 없는 경우 예외 확인")
    public void test_detail_read_not_found_exception() {
        // when
        when(productRepository.findProductDetailById(anyLong())).thenReturn(null);

        // then
        assertThrows(
                NullPointerException.class,
                () -> productReader.readDetail(1L)
        );
    }

    @Test
    @DisplayName("인기상품정보가 없는 경우 예외 확인")
    public void test_find_popular_not_found_exception() {
        // when
        when(productRepository.findPopularProduct(anyInt())).thenReturn(null);

        // then
        assertThrows(
                NullPointerException.class,
                () -> productReader.findPopularProduct(1)
        );
    }
}