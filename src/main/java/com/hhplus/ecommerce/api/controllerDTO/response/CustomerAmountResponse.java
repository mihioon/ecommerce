package com.hhplus.ecommerce.api.controllerDTO.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CustomerAmountResponse {
    private long customerId;
    private String type;
    private BigDecimal amount;

    public CustomerAmountResponse(long customerId, String type, BigDecimal amount){
        this.customerId = customerId;
        this.type = type;
        this.amount = amount;
    }
}
