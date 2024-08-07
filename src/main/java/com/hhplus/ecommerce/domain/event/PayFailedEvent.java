package com.hhplus.ecommerce.domain.event;

import com.hhplus.ecommerce.api.controllerDTO.request.CheckOutRequest;
import lombok.Getter;

@Getter
public class PayFailedEvent extends Event {
    private CheckOutRequest checkOutRequest;

    public PayFailedEvent(String message, CheckOutRequest checkOutRequest) {
        super(message);
        this.checkOutRequest = checkOutRequest;
    }
}
