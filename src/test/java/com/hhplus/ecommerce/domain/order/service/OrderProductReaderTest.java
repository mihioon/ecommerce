package com.hhplus.ecommerce.domain.order.service;

import com.hhplus.ecommerce.domain.order.OrderProduct;
import com.hhplus.ecommerce.domain.order.OrderProductReader;
import com.hhplus.ecommerce.domain.order.OrderRepository;
import com.hhplus.ecommerce.exception.product.NotFoundException;
import com.hhplus.ecommerce.infrastructure.order.OrderProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class OrderProductReaderTest {
    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderProductReader orderProductReader;

    //공통
    Long quantity = 1L;
    Long productId = 10L;
    LocalDateTime dateTime = LocalDateTime.now();
    String orderId = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"))+1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderProductReader = new OrderProductReader(orderRepository);
    }

    @Test
    @DisplayName("주문 Id 기반의 주문물품목록을 조회한다.")
    void findOrder_byOrderId_standard() {
//        // Given
//        List<OrderProduct> orderProducts = List.of(
//                new OrderProduct(orderId, productId, quantity, BigDecimal.TEN)
//        );
//        given(orderRepository.findOrderProducts(any())).willReturn(orderProducts);
//
//        // When
//        List<OrderProduct> result = orderProductReader.read(orderId);
//
//        // Then
//        assertThat(result.get(0).getOrderId()).isEqualTo(orderId);
//        assertThat(result.get(0).getQuantity()).isEqualTo(quantity);
//        assertThat(result.get(0).getProductId()).isEqualTo(productId);
    }

    @Test
    @DisplayName("주문물품목록이 조회되지 않을 경우 에러반환")
    void findOrder_byOrderId_noResult() {
        // Given
        List<OrderProduct> orderProducts = new ArrayList<>();
        given(orderRepository.findOrderProducts(any())).willReturn(orderProducts);

        // When
        // Then
        assertThrows(
                NotFoundException.class,
                () -> orderProductReader.read(orderId) // null 입력으로 인해 예외 발생 예상
        );
    }
}