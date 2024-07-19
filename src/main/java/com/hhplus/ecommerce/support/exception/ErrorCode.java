package com.hhplus.ecommerce.support.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // User
    USER_IS_NOT_FOUND("해당 유저가 존재하지 않습니다."),
    CHARGE_AMOUNT_IS_NEGATIVE("포인트는 0 이상 충전 가능합니다."),
    NOT_ENOUGH_BALANCE("잔액이 충분하지 않습니다."),

    // Payment
    PAYMENT_IS_NOT_FOUND("결제 내역이 존재하지 않습니다."),
    NOT_AVAILABLE_STATE_PAYMENT("결제 가능한 상태가 아닙니다"),
    ALREADY_CANCEL_OR_REFUND("이미 취소됐거나 환불 처리된 결제 정보입니다."),

    // Jwk Token 관련 ErrorCode
    TOKEN_IS_NOT_FOUND("토큰이 존재하지 않습니다."),
    TOKEN_EXPIRED("토큰이 만료되었습니다."),
    INVALID_TOKEN("토큰이 유효하지 않습니다."),
    EXPIRED_TOKEN("토큰이 유효하지 않습니다."),
    INVALID_TOKEN_PAYLOAD("USER ID 형식이 올바르지 않습니다."),
    NOT_EXIST_IN_WAITING_QUEUE("대기열에 토큰이 존재하지 않습니다."),
    TOKEN_IS_NOT_ACTIVE("아직 활성화 되지 않은 토큰입니다."),
    ALREADY_TOKEN_IS_ACTIVE("이미 활성화된 토큰입니다."),

    //
    MALFORMED_HEADER("헤더의 형식이 잘못되었거나 예상한 형식과 맞지 않습니다."),

    ERROR_CODE("에러코드");

    private final String message;
}