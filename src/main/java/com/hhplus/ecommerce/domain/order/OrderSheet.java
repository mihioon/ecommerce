package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.api.controllerDTO.request.OrderSheetRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderSheet {
    private Long id; /* PK : auto increment */
    private Long orderId;  // 주문 아이디는 나중에 설정
    private Long customerId;
    private BigDecimal totalAmount;
    // 주소, 결제타입 etc.

    private List<OrderProduct> orderProducts;
}
