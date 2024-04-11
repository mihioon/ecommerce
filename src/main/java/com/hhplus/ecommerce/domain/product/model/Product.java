package com.hhplus.ecommerce.domain.product.model;

import com.hhplus.ecommerce.domain.customer.model.CustomerAmountHistory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@DynamicUpdate
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; /* Key */

    @Column(nullable = false)
    private String productNm; /* 제품명 */

    @Column(nullable = false)
    private BigDecimal salePrice; /* 제품금액 */

    @Column(nullable = false)
    private Long stockQuantity; /* 제품수량 */

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_statistic_id", referencedColumnName = "id")
    private ProductStatistic productStatistic;
}
