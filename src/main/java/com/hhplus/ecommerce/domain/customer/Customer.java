package com.hhplus.ecommerce.domain.customer;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Customer {
    private Long id;
    private BigDecimal point;

    public Customer(Long id, BigDecimal point) throws IllegalArgumentException {
        // 포인트 유효성 검사
//        if (point.compareTo(BigDecimal.ZERO) < 0) {
//            throw new IllegalArgumentException("point는 0보다 작은 값일 수 없습니다.");
//        }
        this.id = id;
        this.point = point;
    }

    public void chargePoint(BigDecimal point){
        if (point.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("추가될 point는 0보다 커야합니다.");
        }
        this.point = this.point.add(point);
    }

    public void usePoint(BigDecimal point){
        if (this.point.compareTo(point) < 0) {
            throw new IllegalArgumentException("사용할 point는 현재 point보다 크거나 같아야합니다.");
        }
        this.point = this.point.subtract(point);
    }
}
