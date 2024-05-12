package com.hhplus.ecommerce.infrastructure.product.entity;

import com.hhplus.ecommerce.domain.product.object.Product;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@Table(name = "product_statistic")
public class ProductStatisticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; /* Key */

    private Long checkOutCnt; /* 구매횟수 */

    private Long cartCnt; /* 카트횟수 */

    private Long prodClickCnt; /* 상품클릭횟수 */

    private Long searchCnt; /* 검색횟수 */

    @OneToOne(mappedBy = "productStatistic")
    private Product product;
}
