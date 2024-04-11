package com.hhplus.ecommerce.api.payment.controller;

import com.hhplus.ecommerce.api.payment.controller.mapper.PaymentDTOmapper;
import com.hhplus.ecommerce.api.payment.controllerDTO.PaymentCheckOutRequest;
import com.hhplus.ecommerce.api.payment.controllerDTO.PaymentCheckOutResponse;
import com.hhplus.ecommerce.api.product.controller.mapper.ProductDTOmapper;
import com.hhplus.ecommerce.api.product.controllerDTO.ProductPopularItemResponse;
import com.hhplus.ecommerce.domain.customer.service.serviceDTO.CustomerDTO;
import com.hhplus.ecommerce.domain.payment.service.PaymentService;
import com.hhplus.ecommerce.domain.payment.service.serviceDTO.PaymentDTO;
import com.hhplus.ecommerce.domain.product.service.ProductService;
import com.hhplus.ecommerce.exception.product.ProductsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentDTOmapper mapper;

    // 주문 / 결제 api
    @PostMapping("/checkOut")
    public ResponseEntity<?> customerCartCheckOut(@RequestBody PaymentCheckOutRequest param){
        try {
            PaymentDTO paymentDTO = mapper.requestToPaymentDTO(param);

            PaymentCheckOutResponse result = PaymentCheckOutResponse.builder()
                    .code("SUCCESS")
                    .message("결제되었습니다.")
                    .body(paymentService.cartCheckOut(paymentDTO))
                    .build();
            return ResponseEntity.ok(result);
        } catch (ProductsNotFoundException e){
            PaymentCheckOutResponse errorResult = PaymentCheckOutResponse.builder()
                    .code("ERROR")
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.ok(errorResult);
        }
    }

}
