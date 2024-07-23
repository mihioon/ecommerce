package com.hhplus.ecommerce.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductManagerTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductManager productManager;

    @Test
    @DisplayName("저장하려는 상품이 null 인 경우 예외 확인")
    public void test_product_is_null() {
        // given
        Product product = null;

        // then
        assertThrows(
                NullPointerException.class,
                () -> productManager.save(null)
        );
    }
}