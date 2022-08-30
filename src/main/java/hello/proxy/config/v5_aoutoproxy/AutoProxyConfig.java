package hello.proxy.config.v5_aoutoproxy;

import hello.advanced.trace.logtrace.LogTrace;
import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {

    //advisor만 등록하면 자동으로 빈 등록전 전시간에 진행한 빈 후처리기에 대한 동작을 수행해줌. 모든 빈 클래스를 보며 포인트컷의 조건에 부합하는 내용이 있다면 프록시 형태로 컨테이너에 등록, 아니라면 원본을 등록한다. 단 어드바이저 안에 포인트컷과 어드바이스가 있어야하긴 함.
    //여기서 advisor는 결국 @Bean어노테이션의 리턴값이 일반 값인지 Advisor인지 로 구분하는듯 하다
//    @Bean
    public Advisor getAdvisor1(LogTrace logTrace) {
        //pointcur
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        //advice
        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }

//    @Bean
    public Advisor getAdvisor2(LogTrace logTrace) {
        //pointcur
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* hello.proxy.app..*(..))");
        //advice
        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }

    @Bean
    public Advisor getAdvisor3(LogTrace logTrace) {
        //pointcur
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..))");
        //advice
        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }
}
