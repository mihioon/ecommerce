package com.hhplus.ecommerce.domain;

import org.springframework.stereotype.Component;

@Component
public class NullChecker {
    public static <T> void checkNotNull(T object, String variableName) {
        if (object == null) {
            throw new NullPointerException("해당 객체가 존재하지 않습니다. : " + variableName);
        }
    }
}
