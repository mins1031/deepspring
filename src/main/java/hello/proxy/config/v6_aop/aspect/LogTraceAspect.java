package hello.proxy.config.v6_aop.aspect;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

@Slf4j
@Aspect // 어드바이저를 편리하게 어노테이션으로 만들어준것이라고 이d
public class LogTraceAspect {

    private LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    //즉 스프링 컨테이너는 @Aspect 어노테이션을 어드바이저로 보고 @Around의 내용을 포인트컷  @Around가 붙은 메서드를 어드바이스로 판단한다.
    @Around("execution(* hello.proxy.app..*(..))")//포인트컷을 어노테이션으로 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        //어드바이스 로직 작성.
        TraceStatus status = null;
        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            //target 호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }

    }
}
