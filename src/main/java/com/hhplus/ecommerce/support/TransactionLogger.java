package com.hhplus.ecommerce.support;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TransactionLogger {

    private static int lockTryCnt = 0;

    public static void plusLockTryCnt() {
        lockTryCnt++;
    }

    @Pointcut("execution(* com.hhplus.ecommerce.domain.product.StockService.*(..)) && @annotation(org.springframework.transaction.annotation.Transactional)")
    public void transactionalMethods() {}

    @Before("transactionalMethods()")
    public void beforeTransaction() {
        log.info("💚========> Transaction started");
    }

    @After("transactionalMethods()")
    public void afterTransaction() {
        log.info("lockTryCnt : " + lockTryCnt);
        log.info("❤️========> Transaction completed");
    }
}