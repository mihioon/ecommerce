package com.hhplus.ecommerce.infrastructure.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
@Table(name = "product_statistic")
public class ProductStatisticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId; /* Key */

    private Long checkOutCnt; /* 구매횟수 */

    private Long cartCnt; /* 카트횟수 */

    private Long prodClickCnt; /* 상품클릭횟수 */

    private Long searchCnt; /* 검색횟수 */
}
