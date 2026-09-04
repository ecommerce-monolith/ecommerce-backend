package com.huyhn.ecommerce_backend.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(1)
public class LoggingAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) " +
            "&& within(com.huyhn.ecommerce_backend.module..*)")
    public void controllerMethods() {
    }

    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            log.debug("Method {} executed in {}ms, returned={}",
                    methodName, System.currentTimeMillis() - start, normalizeResult(result));
            return result;
        } catch (Exception ex) {
            log.debug("Method {} threw after {}ms: {}",
                    methodName, System.currentTimeMillis() - start, ex.getMessage(), ex);
            throw ex;
        }
    }

    private String normalizeResult(Object result) {
        if (result == null) {
            return "null";
        }
        String resultString = result.toString();
        if (resultString.length() > 1000) {
            return resultString.substring(0, 1000) + "...(truncated)";
        }
        return resultString;
    }
}
