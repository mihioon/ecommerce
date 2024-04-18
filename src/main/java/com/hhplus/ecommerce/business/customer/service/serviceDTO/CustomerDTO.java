package com.hhplus.ecommerce.business.customer.service.serviceDTO;

import com.hhplus.ecommerce.business.customer.model.Customer;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerDTO {
    // 서비스 - 컨트롤러 간 데이터 전송을 위한 객체
    private long customerId;
    private String type;
    private BigDecimal amount;

    public CustomerDTO(long customerId, String type, BigDecimal amount) {
        this.customerId = customerId;
        this.type = type;
        this.amount = amount;
    }

    public CustomerDTO(long customerId, BigDecimal amount) {
        this.customerId = customerId;
        this.amount = amount;
    }
}
