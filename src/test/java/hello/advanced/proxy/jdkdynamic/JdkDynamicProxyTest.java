package hello.advanced.proxy.jdkdynamic;

import hello.advanced.proxy.jdkdynamic.code.AImpl;
import hello.advanced.proxy.jdkdynamic.code.AInterface;
import hello.advanced.proxy.jdkdynamic.code.BImpl;
import hello.advanced.proxy.jdkdynamic.code.BInterface;
import hello.advanced.proxy.jdkdynamic.code.TimeInvocationHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface target = new AImpl();

        //클래스마다 프록시 클래스를 만드는 비효율성을 해결하기 위해 동적으로 프록시 객체를 생서하기위해 해당 핸들러를 구현하고 여기에 실행할 내용을 정의한 객체를 인자로 전달해 탬플린 메서드 패턴처럼 구현한다.
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);

        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call2();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);

        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }
}
