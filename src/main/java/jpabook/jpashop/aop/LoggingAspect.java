package jpabook.jpashop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Around("execution(* jpabook.jpashop.controller.HomeController.*(..) )")
    public Object timeCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        long startAt = System.currentTimeMillis();
        log.info("#### start: "  + startAt);
        Object proceed = joinPoint.proceed();
        long endAt = System.currentTimeMillis();
        log.info("#### end: "  + endAt);
        System.out.println("걸린 시간 : " + (endAt-startAt) + "ms");
        return proceed;
    }
}
