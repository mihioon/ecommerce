package com.hhplus.ecommerce.infrastructure.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product_datail")
public class ProductDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; /* Key */

    @Column(nullable = false)
    private Long stockQuantity; /* 제품수량 */

    @Column
    private Long likeCnt; /* 좋아요 수 */

    @Column
    private Long dayOrderCnt; /* 당일 주문 수 */

    public void minusQuantity(Long orderQuantity) {
        this.stockQuantity = stockQuantity - orderQuantity;
    }

    public void plusQuantity(Long orderQuantity) {
        this.stockQuantity =  stockQuantity + orderQuantity;
    }
}