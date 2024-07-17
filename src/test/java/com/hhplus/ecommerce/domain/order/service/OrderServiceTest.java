package com.hhplus.ecommerce.domain.order.service;

import com.hhplus.ecommerce.domain.order.Order;
import com.hhplus.ecommerce.domain.order.OrderService;
import com.hhplus.ecommerce.util.OrderState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    OrderService orderService;
    // 주문 파사드 통합 테스트
    @Test
    @DisplayName("주문생성 및 주문조회 테스트")
    void order_create_test() {
        // Given
        String orderCode = "orderCode";
        Long customerId = 1L;
        BigDecimal totalPrice = new BigDecimal("1000");
        Order order = new Order(orderCode, customerId, totalPrice);

        // When
        Long orderId = orderService.createOrder(order);
        Order result = orderService.findOrder(orderId);

        // Then
        assertThat(result.getId()).isEqualTo(orderId);
        assertThat(result.getOrderCode()).isEqualTo(orderCode);
        assertThat(result.getCustomerId()).isEqualTo(customerId);
        assertThat(result.getOrderState()).isEqualTo(OrderState.NEW);
        assertThat(result.getTotalPrice()).isEqualByComparingTo(totalPrice);
    }
}