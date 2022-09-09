package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV2 {

    //hello.aop.order 패키지와 하위 패키지에 대한 표현
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder() {} // pointcut signature

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
        return joinPoint.proceed();
    }

    @Around("allOrder()")
    public Object doLog2(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
        return joinPoint.proceed();
    } // 이렇게 공통된 포인트컷 조건에 여러가지 어드바이스를 적용해줘야 하는 경우 포인트컷을 따로 메서드로 빼는게 유용하다. 또한 포인트컷에 대해 이름을 부여할수 있어 가독성에도 좋다.


}
