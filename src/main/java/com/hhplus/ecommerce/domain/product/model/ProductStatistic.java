package com.hhplus.ecommerce.domain.product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@DynamicUpdate
@Table(name = "product_statistic")
public class ProductStatistic {
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
