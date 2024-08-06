package com.hhplus.ecommerce.api.controllerDTO.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PointChargeRequest {
    public Long customerId;
    public BigDecimal point;
}
