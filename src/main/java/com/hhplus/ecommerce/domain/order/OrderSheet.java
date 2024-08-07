package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.domain.product.StockCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class OrderSheet {
    private Long id; /* PK : auto increment */
    private Long orderId;  // 주문 아이디는 나중에 설정
    private Long customerId;
    private BigDecimal totalAmount;
    // 주소, 결제타입 etc.

    private List<OrderProduct> orderProductList;

    public Order toOrder(){
        return new Order(
               this.id,
               null,
               this.customerId,
               Order.State.NEW,
               this.totalAmount,
               this.orderProductList
        );
    }

    public List<StockCommand> toStockList(){
        return this.orderProductList.stream()
                .map(orderProduct -> new StockCommand(
                        orderProduct.getProductId(),
                        orderProduct.getQuantity()
                ))
                .collect(Collectors.toList());
    }
}
