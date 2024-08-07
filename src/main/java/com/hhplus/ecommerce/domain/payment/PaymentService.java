package com.hhplus.ecommerce.domain.payment;

import com.hhplus.ecommerce.domain.event.PayCompletedPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    IamportService iamportService;
    PayCompletedPublisher publisher;

    public boolean pay(Payment payment) {
        Payment.Type type = payment.getType();
        String uid = "";
        boolean paySuccess = false;
        Long paymentId = payment.getId();

        // 분기 처리
        switch (type) {
            case PAY:
                // 페이서비스
                paySuccess = true;
                break;

            case IAMPORT: // 아임포트 처리
                iamportService.processIamportPayment(uid);
                break;

            default:
                throw new IllegalArgumentException("결제 방법이 잘못됨");
        }

        return paySuccess;
    }
}
