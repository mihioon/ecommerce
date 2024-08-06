package com.hhplus.ecommerce.api.controllerDTO.request;

import com.hhplus.ecommerce.domain.order.OrderProduct;
import com.hhplus.ecommerce.domain.order.OrderSheet;
import com.hhplus.ecommerce.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderSheetRequest {
    private Long id;
    private Long orderId;  // 주문 아이디는 나중에 설정
    private Long userId;
    private BigDecimal totalPrice;

    private List<ItemRequest> items;
    // 주소, 결제타입 etc.

    @Getter
    public static class ItemRequest {
        private Long itemId;
        private String itemName;
        private int quantity;
        private BigDecimal unitPrice;
        // 할인 쿠폰 등등
    }

    public OrderSheet toOrderSheet(){
        List<OrderProduct> orderProductList = null;
        OrderSheet orderSheet = new OrderSheet(
                null,
                null,
                this.userId,
                this.totalPrice,
                orderProductList
        );
        return orderSheet;
    }
}
