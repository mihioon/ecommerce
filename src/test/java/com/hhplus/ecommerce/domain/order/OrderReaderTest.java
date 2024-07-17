package com.hhplus.ecommerce.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderReaderTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderReader orderReader;

    @Test
    @DisplayName("주문이 없는 경우 예외 확인")
    public void test_order_not_found_exception() {
        // when
        when(orderRepository.findOrder(anyLong())).thenReturn(null);

        // then
        assertThrows(
                NullPointerException.class,
                () -> orderReader.read(1L)
        );
    }
}