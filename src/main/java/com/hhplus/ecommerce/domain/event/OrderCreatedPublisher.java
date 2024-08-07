package com.hhplus.ecommerce.domain.event;

import com.hhplus.ecommerce.api.controllerDTO.request.CheckOutRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCreatedPublisher {
    private final ApplicationEventPublisher publisher;

    public void publish(CheckOutRequest checkOutRequest) {
        OrderCreatedEvent event = new OrderCreatedEvent("OrderCreated", checkOutRequest);
        log.info("이벤트 발행: " + event.getMessage());
        publisher.publishEvent(event);
    }
}