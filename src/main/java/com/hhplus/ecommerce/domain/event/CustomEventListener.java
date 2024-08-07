package com.hhplus.ecommerce.domain.event;

import com.hhplus.ecommerce.domain.payment.Payment;
import com.hhplus.ecommerce.domain.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomEventListener {
    PaymentService paymentService;

    @EventListener
    public void listen(Event event) {

    }

    @EventListener
    public void handleEvent(OrderCreatedEvent event) {
        log.info("이벤트 발행: " + event.getMessage());
        // 외부 연동 결제
        Payment payment = event.getCheckOutRequest().toPayment();
        paymentService.pay(payment);
    }
}
