package com.hhplus.ecommerce.infrastructure.product.entity;

import com.hhplus.ecommerce.domain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; /* Key */

    @Column(nullable = false)
    private String productNm; /* 제품명 */

    @Column(nullable = false)
    private BigDecimal salePrice; /* 제품금액 */

    @Column(nullable = false)
    private Long stockQuantity; /* 제품수량 */

    public void updateQuantity(Long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Product toDomain(){
        return new Product(this.id,
                this.productNm,
                this.salePrice,
                this.stockQuantity);
    }
}
