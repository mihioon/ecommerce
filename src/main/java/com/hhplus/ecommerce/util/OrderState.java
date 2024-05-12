package com.hhplus.ecommerce.util;

public enum OrderState {
    NEW, /* 생성 */
    PENDING, /* 주문접수 */
    PAID, /* 결제완료 */
    PROCESS, /* 주문처리중 */
    SHIPPED, /* 배송시작 */
    CANCEL, /* 주문취소 */
    REFUND, /* 환불 */
}