package com.izeye.sample;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging.
 *
 * @author Johnny Lim
 */
@Component
@Aspect
@Slf4j
@Order
public class LoggingAspect {

    @Around("execution (@com.izeye.sample.Logging * *.*(..))")
    public Object logMethod(ProceedingJoinPoint pjp) throws Throwable {
        log.info("Logging in LoggingAspect...");
        return pjp.proceed();
    }

}
