package com.hhplus.ecommerce.domain.event;

import com.hhplus.ecommerce.api.controllerDTO.request.CheckOutRequest;
import lombok.Getter;

@Getter
public class OrderCreatedEvent extends Event {
    private final CheckOutRequest checkOutRequest;

    public OrderCreatedEvent(String message, CheckOutRequest checkOutRequest) {
        super(message);
        this.checkOutRequest = checkOutRequest;
    }
}