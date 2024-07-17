package com.hhplus.ecommerce.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderAppenderTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderAppender orderAppender;

    @Test
    @DisplayName("주문생성에 실패한 경우 예외 확인")
    public void test_order_persist_fail_exception() {
        // when
        when(orderRepository.persistOrder(any())).thenReturn(null);

        // then
        assertThrows(
                NullPointerException.class,
                () -> orderAppender.append(null)
        );
    }
}