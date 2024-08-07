package com.hhplus.ecommerce.domain.event;

import com.hhplus.ecommerce.api.controllerDTO.request.CheckOutRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayCompletedPublisher {
    private final ApplicationEventPublisher publisher;

    public void success(CheckOutRequest checkOutRequest) {
        PayCompleteEvent event = new PayCompleteEvent("PayCompleted", checkOutRequest);
        log.info("이벤트 발행: " + event.getMessage());
        publisher.publishEvent(event);
    }

    public void fail(CheckOutRequest checkOutRequest) {
        PayFailedEvent event = new PayFailedEvent("PayFailed", checkOutRequest);
        log.info("이벤트 발행: " + event.getMessage());
        publisher.publishEvent(event);
    }
}