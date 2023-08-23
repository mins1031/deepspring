package hello.advanced.proxy.pureproxy.proxy;

import hello.advanced.proxy.pureproxy.proxy.code.CacheProxy;
import hello.advanced.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.advanced.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient proxyPatternClient = new ProxyPatternClient(realSubject);

        long startTime = System.currentTimeMillis();
        proxyPatternClient.execute();
        proxyPatternClient.execute();
        proxyPatternClient.execute();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    @Test
    void cacheProxyTest() {
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

        long startTime = System.currentTimeMillis();
        client.execute();
        client.execute();
        client.execute();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}
