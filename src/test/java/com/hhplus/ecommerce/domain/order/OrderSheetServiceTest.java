package com.hhplus.ecommerce.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderSheetServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderSheetService orderSheetService;

    @Test
    @DisplayName("주문서가 없는 경우 예외 확인")
    public void test_orderSheet_not_found_exception() {
        // when
        when(orderRepository.findOrderSheet(anyLong())).thenReturn(null);

        // then
        assertThrows(
                NullPointerException.class,
                () -> orderSheetService.read(1L)
        );
    }
}