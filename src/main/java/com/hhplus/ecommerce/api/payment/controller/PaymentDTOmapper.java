package com.hhplus.ecommerce.api.payment.controller;

import com.hhplus.ecommerce.api.customer.controllerDTO.CustomerAmountRequest;
import com.hhplus.ecommerce.api.customer.controllerDTO.CustomerAmountResponse;
import com.hhplus.ecommerce.api.payment.controllerDTO.PaymentCheckOutRequest;
import com.hhplus.ecommerce.api.payment.controllerDTO.PaymentCheckOutResponse;
import com.hhplus.ecommerce.api.payment.controllerDTO.PaymentProductRequest;
import com.hhplus.ecommerce.api.payment.controllerDTO.PaymentProductResponse;
import com.hhplus.ecommerce.business.customer.service.serviceDTO.CustomerDTO;
import com.hhplus.ecommerce.business.payment.service.serviceDTO.PaymentDTO;
import com.hhplus.ecommerce.business.product.service.serviceDTO.ProductDTO;
import com.hhplus.ecommerce.exception.ConvertNumber;
import com.hhplus.ecommerce.exception.MapperException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class PaymentDTOmapper {
    private final ConvertNumber convertNumber;
    public PaymentDTOmapper(ConvertNumber convertNumber){
        this.convertNumber = convertNumber;
    }

    public PaymentDTO requestToPaymentDTO(PaymentCheckOutRequest param) {
        if(param == null){
            throw new MapperException("입력 값이 없습니다.");
        }
        ProductDTO[] productDTOs = Arrays.stream(param.productList)
                .map(this::convertProductDTO).toArray(ProductDTO[]::new);

        return new PaymentDTO(
                convertNumber.convertLong(param.customerId),
                null,
                productDTOs
        );
    }

    public ProductDTO convertProductDTO(PaymentProductRequest param) {
        return new ProductDTO(convertNumber.convertLong(param.getProductId()),
                convertNumber.convertBigDecimal(param.getSalePrice()),
                convertNumber.convertLong(param.getQuantity()));

    }

    public PaymentProductResponse convertProductDTO(ProductDTO param) {
        return new PaymentProductResponse(param.getProductId(),
                                        param.getProductNm(),
                                        param.getSalePrice(),
                                        param.getStockQuantity()
        );

    }

    public PaymentCheckOutResponse paymentDtoToResponse(PaymentDTO param) {
        PaymentProductResponse[] productResponses = Arrays.stream(param.getProductList())
                .map(this::convertProductDTO).toArray(PaymentProductResponse[]::new);
        return new PaymentCheckOutResponse(
                param.getCustomerId(),
                productResponses
        );
    }

}
