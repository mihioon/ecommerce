package com.hhplus.ecommerce.domain.event;

import com.hhplus.ecommerce.api.controllerDTO.request.CheckOutRequest;
import com.hhplus.ecommerce.domain.payment.Payment;
import com.hhplus.ecommerce.domain.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreatedListener {
    PayCompletedPublisher payCompletedPublisher;
    PaymentService paymentService;

    @EventListener
    public void handleEvent(OrderCreatedEvent event) {
        log.info("이벤트 수신: " + event.getMessage());
        CheckOutRequest checkOutRequest = event.getCheckOutRequest();
        // 외부 연동 결제
        Payment payment = checkOutRequest.toPayment();
        boolean paySuccess = paymentService.pay(payment);

        if (paySuccess) {
            payCompletedPublisher.success(checkOutRequest);
        } else {
            payCompletedPublisher.fail(checkOutRequest);
        }
    }
}
