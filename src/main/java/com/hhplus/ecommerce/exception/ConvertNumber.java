package com.hhplus.ecommerce.exception;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConvertNumber {
    public BigDecimal convertBigDecimal(String string) {
        BigDecimal result;
        try {
            result = new BigDecimal(string);
        } catch (NumberFormatException e) {
            // 입력 문자열이 숫자 형식이 아닌 경우 예외 처리(Null 포함)
            throw new MapperException("입력 값이 숫자 형식이 아닙니다.");
        }

        // 입력 값이 0 이하인지 검사
        if (result.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MapperException("0 이하의 값은 허용되지 않습니다.");
        }

        return result;
    }

    public Long convertLong(String string) throws NumberFormatException {
        Long result;
        try {
            result = Long.valueOf(string);
        } catch (NumberFormatException e) {
            // 입력 문자열이 숫자 형식이 아닌 경우 예외 처리(Null 포함)
            throw new MapperException("입력 값이 숫자 형식이 아닙니다.");
        }

        // 입력 값이 0 이하인지 검사
        if (result <= 0) {
            throw new MapperException("0 이하의 값은 허용되지 않습니다.");
        }

        return result;
    }
}
