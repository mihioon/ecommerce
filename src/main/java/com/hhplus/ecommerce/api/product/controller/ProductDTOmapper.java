package com.hhplus.ecommerce.api.product.controller;

import com.hhplus.ecommerce.exception.ConvertNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOmapper {
    @Autowired
    ConvertNumber convertNumber;

    public Long convertProductId(String param){
        return convertNumber.convertLong(param);
    }
}
