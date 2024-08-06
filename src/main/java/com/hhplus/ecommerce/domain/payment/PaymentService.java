package com.hhplus.ecommerce.domain.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    IamportService iamportService;

    public void pay(Payment payment) {
        Payment.Type type = payment.getType();
        String uid = "";

        // 분기 처리
        switch (type) {
            case PAY:
                // 페이서비스
                break;

            case IAMPORT: // 아임포트 처리
                iamportService.processIamportPayment(uid);
                break;

            default:
                throw new IllegalArgumentException("결제 방법이 잘못됨");
        }
    }
}
