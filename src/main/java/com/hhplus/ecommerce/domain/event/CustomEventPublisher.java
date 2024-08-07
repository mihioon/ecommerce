package com.hhplus.ecommerce.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomEventPublisher {
    private final ApplicationEventPublisher publisher;

    public void publish() throws InterruptedException {
        publisher.publishEvent(new Event("Event"));
    }
}