package com.hhplus.ecommerce.domain.order.service;

import com.hhplus.ecommerce.domain.order.OrderReader;
import com.hhplus.ecommerce.domain.order.OrderRepository;
import com.hhplus.ecommerce.infrastructure.order.OrderEntity;
import com.hhplus.ecommerce.util.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class OrderReaderTest {
    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderReader orderReader;

    //공통
    Long customerId = 1L;
    Long productId = 10L;
    LocalDateTime dateTime = LocalDateTime.now();
    String orderId = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"))+customerId;
    OrderEntity orderEntity = new OrderEntity(orderId, customerId, dateTime, OrderState.PAID, BigDecimal.TEN);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderReader = new OrderReader(orderRepository);
    }

    @Test
    @DisplayName("주문 Id 기반의 주문을 조회한다.")
    void findOrder_byOrderId_standard() {
//        // Given
//        Order order = new Order(orderId, customerId, dateTime, OrderState.PAID, BigDecimal.TEN);
//        given(orderRepository.findOrder(any())).willReturn(orderEntity);
//
//        // When
//        OrderEntity result = orderReader.read(orderId);
//
//        // Then
//        assertThat(result.getOrderId()).isEqualTo(orderId);
//        assertThat(result.getCustomerId()).isEqualTo(customerId);
    }
}