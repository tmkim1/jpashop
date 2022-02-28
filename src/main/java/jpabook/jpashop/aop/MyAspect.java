//package jpabook.jpashop.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class MyAspect {
//    // execution() : joinpoint에 함수를 실행한다.
//    // 제어자 : public
//    // 반환타입 : ProductVO
//    // 패키지 : com.victolee.aoptest
//    // 클래스 : ProductServcie
//    // 메서드 : find
//    // 예외 던지기 생략 가능
////    @Before("execution(public ProductVO com.victolee.aoptest.ProductService.find(..) )") // joinpoint 지정
//    public void beforeAdvice() {
//        System.out.println("beforeAdvice() called");
//    }
//
//
//    // 접근제어자 생략 가능
//    // 반환타입 : 모든 타입
//    // 패키지 : com.victolee.aoptest
//    // 클래스 : 패키지 내의 모든 클래스
//    // 메서드 : find
////    @After("execution(* com.victolee.aoptest.*.find(..) )")
//    public void afterAdvice() {
//        System.out.println("afterAdvice() called");
//    }
//
//
//    // 모든 패키지 내 ProductServcie 클래스의 모든 메서드
////    @AfterReturning("execution(* *..HomeController.*(..))")
//    public void afterReturningAdvice() {
//        System.out.println("afterReturningAdvice() called");
//    }
//
//
//    // 모든 패키지의 모든 클래스의 모든 메서드
//    // ProductService에서 예외를 던질 경우 그 예오를 받는다.
//    // 예를들어, if(false) throw new RuntimeException("ProductService Exception 발생 ! ");
//    @AfterThrowing(value="execution(* *..*.*(..))", throwing="ex")
//    public void afterThrowingAdvice(Throwable ex) {
//        System.out.println("afterThrowingAdvice() called: " + ex);
//    }
//
//
//    // pjp를 통해 "핵심 모듈의 메서드"의 실행이 가능
//    // aoptest 패키지의 모든 클래스 모든 메서드 ( execution은 일반적으로 이렇게 쓰인다. )
//    @Around("execution(* *..aoptest.*.*(..))")
//    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
//        // before advice
//        System.out.println("aroundAdvice(): before");
//
//        // proceed() 메서드 호출 => 실제 비즈니스
//        // 비즈니스가 리턴하는 객체가 있을 수 있으므로 Obejct로 받아준다.
//        Object result = pjp.proceed();
//
//        // after advice
//        System.out.println("aroundAdvice(): after");
//
//        return result;
//    }
//}