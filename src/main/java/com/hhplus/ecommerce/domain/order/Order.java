package com.hhplus.ecommerce.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class Order {
    private Long id; /* Key */
    private String orderCode;
    private Long customerId;
    private State orderState; /* 주문 상태 */
    private BigDecimal totalPrice; /* 총 주문금액 */

    private List<OrderProduct> orderProducts;

    public enum State {
        FAILED, /* 실패 */
        NEW, /* 생성 */
        PENDING, /* 주문접수 */
        PAID, /* 결제완료 */
        PROCESS, /* 주문처리중 */
        SHIPPED, /* 배송시작 */
        CANCEL, /* 주문취소 */
        REFUND, /* 환불 */
    }
}
