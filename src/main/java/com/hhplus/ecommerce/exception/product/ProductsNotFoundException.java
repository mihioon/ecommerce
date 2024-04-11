package com.hhplus.ecommerce.exception.product;

public class ProductsNotFoundException extends RuntimeException{
    public ProductsNotFoundException(String message) {
        super(message);
    }
}
