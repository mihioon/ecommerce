package com.hhplus.ecommerce.domain.order.service;

import com.hhplus.ecommerce.domain.order.object.OrderHistory;
import com.hhplus.ecommerce.domain.order.repository.OrderRepository;
import com.hhplus.ecommerce.exception.product.NotFoundException;
import com.hhplus.ecommerce.infrastructure.order.entity.OrderEntity;
import com.hhplus.ecommerce.infrastructure.order.entity.OrderProductEntity;
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
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    //공통
    Long customerId = 1L;
    Long productId = 10L;
    LocalDateTime dateTime = LocalDateTime.now();
    String orderId = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"))+customerId;
    OrderEntity orderEntity = new OrderEntity(orderId, customerId, dateTime, OrderState.PAID, BigDecimal.TEN);


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderRepository);
    }

    @Test
    @DisplayName("주문 Id 기반의 주문 및 주문 물품목록을 조회한다.")
    void findOrder_byOrderId_standard() {
        // Given
        List<OrderProductEntity> orderProducts = List.of(
                new OrderProductEntity(orderEntity, productId, customerId, BigDecimal.TEN)
        );
        OrderEntity orderEntity = new OrderEntity(orderId, customerId, dateTime, OrderState.PAID, BigDecimal.TEN, orderProducts);
        given(orderRepository.findOrder(any())).willReturn(orderEntity);

        // When
        OrderHistory orderHistory = orderService.findOrder(orderId);

        // Then
        assertThat(orderHistory.getOrderId()).isEqualTo(orderId);
        assertThat(orderHistory.getCustomerId()).isEqualTo(customerId);
    }

    @Test
    @DisplayName("주문 Id 기반의 주문이 없을 경우 에러를 반환한다.")
    void findOrder_byOrderId_noId() {
        // Given
        OrderEntity orderEntity = null;
        given(orderRepository.findOrder(any())).willReturn(orderEntity);

        // When
        // Then
        assertThrows(
                NotFoundException.class,
                () -> orderService.findOrder(orderId) // null 입력으로 인해 예외 발생 예상
        );
    }

    @Test
    @DisplayName("주문 Id 기반의 주문은 존재하나 주문상세내역이 없을 경우 에러를 반환한다.")
    void findOrder_byOrderId_noProduct() {
        // Given
        given(orderRepository.findOrder(any())).willReturn(orderEntity);

        // When
        // Then
        assertThrows(
                NotFoundException.class,
                () -> orderService.findOrder(orderId) // null 입력으로 인해 예외 발생 예상
        );
    }
}