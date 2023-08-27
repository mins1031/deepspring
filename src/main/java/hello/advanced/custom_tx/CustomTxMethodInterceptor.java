package hello.advanced.custom_tx;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

@Slf4j
public class CustomTxMethodInterceptor implements MethodInterceptor {
    private final Object targetObject;

    public CustomTxMethodInterceptor(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        CustomTransactional customTransactional = method.getDeclaredAnnotation(CustomTransactional.class);
        Object invoke = null;
        if (customTransactional == null) {
            methodProxy.invoke(targetObject, args);
        }

        if (customTransactional != null) {
            log.info("start transaction");
            try {
                invoke = methodProxy.invoke(targetObject, args);
            } catch (Exception e) {
                log.info("rollback");
                throw e;
            }

            Thread.sleep(1000);
            log.info("commit");
        }
        return invoke;
    }
}
