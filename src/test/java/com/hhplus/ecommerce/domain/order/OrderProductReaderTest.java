package com.hhplus.ecommerce.domain.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderProductReaderTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderProductReader orderProductReader;

    @Test
    @DisplayName("주문물품목록이 조회되지 않을 경우 에러반환")
    void find_order_byOrderId_noResult() {
        // when
        when(orderRepository.findOrderProducts(any())).thenReturn(null);

        // Then
        assertThrows(
                NullPointerException.class,
                () -> orderProductReader.read(1L)
        );
    }
}