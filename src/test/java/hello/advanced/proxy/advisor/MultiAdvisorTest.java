package hello.advanced.proxy.advisor;

import hello.advanced.proxy.common.advice.TimeAdvice;
import hello.advanced.proxy.common.service.ServiceImpl;
import hello.advanced.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class MultiAdvisorTest {

    @DisplayName("여러 프록시")
    @Test
    void multiAdvisorTest1() {
        //client -> proxy2(advisor2) -> proxy1(advisor1) -> target

        //프록시1 생성
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory1 = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        proxyFactory1.addAdvisor(advisor1);
        ServiceInterface proxy1 = (ServiceInterface) proxyFactory1.getProxy();

        //프록시2 생성
        ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
        proxyFactory2.addAdvisor(advisor2);
        ServiceInterface proxy2 = (ServiceInterface) proxyFactory2.getProxy();

        proxy2.save();
        //결국 위 과정은 여러개의 프록시 로직(advice)을 앞에서 실행하고 본로직(save())을 실행하기 위한 과정이라고 생각하면 될듯?
        //if save 실행전 시간계산 로직과 로그 저장 로직이 있어야할때 로그 저장을 == Advice2 , 시간 계산을 == Advice1 위 로직들을 먼저 실행후 본 로직인 target의 save 메서드 내용을 수행하게 되는 과정.
    }

    @DisplayName("하나의 프록시, 여러 어드바이저")
    @Test
    void multiAdvisorTest2() {
        //client -> proxy -> advisor2 -> advisor1 -> target

        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());

        //프록시1 생성
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(advisor2);
        proxyFactory.addAdvisor(advisor1);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();

        //위 내용은 하나의 프록시와 여러 어드바이저를 통해 어드바이저 별로 프록시를 생성해서 사용하지 않고 하나의 프록시를 통해 여러 어드바이저의 내용을 수행하는 것을 보여준다
        //결국 1. 하나의 클래스를 팩토리에 등록시킨다 -> 2. 여러 어드바이저를 등록 -> 3. 각 어드바이저 포인트컷에 따라 같이 등록된 어드바이스가 실행될지 말지 결정 (어드바이저 등록 순서대로 진행)
    }
    @Slf4j
    static class Advice1 implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice1 호출");
            return invocation.proceed();
        }
    }

    @Slf4j
    static class Advice2 implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice2 호출");
            return invocation.proceed();
        }
    }
}
