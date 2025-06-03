package org.tukorea.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrderLoggingAspect {
	// 주문 시작 로그 출력
    @Before("execution(* org.tukorea.free.service.OrderService.placeOrder(..))")
    public void logBeforePlaceOrder(JoinPoint joinPoint) {
        System.out.println("[ORDER] 주문 시작");
        System.out.println(" 메서드: " + joinPoint.getSignature().getName());
        System.out.println(" 주문자 ID: " + joinPoint.getArgs()[0]); // OrderDTO
    }
    
	// 주문 성공 로그 출력
    @AfterReturning("execution(* org.tukorea.free.service.OrderService.placeOrder(..))")
    public void logAfterSuccess(JoinPoint joinPoint) {
        System.out.println("[ORDER] 주문 성공");
    }
    
	// 주문 실패 로그 출력
    @AfterThrowing(pointcut = "execution(* org.tukorea.free.service.OrderService.placeOrder(..))", throwing = "ex")
    public void logAfterThrowing(Throwable ex) {
        System.out.println("[ERROR] 주문 실패 - 예외 발생");
        System.out.println(" 예외 메시지: " + ex.getMessage());
    }
}
