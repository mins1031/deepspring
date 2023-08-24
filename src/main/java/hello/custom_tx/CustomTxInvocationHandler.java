package hello.custom_tx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class CustomTxInvocationHandler implements InvocationHandler {
    private final Object targetObject;

    public CustomTxInvocationHandler(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (!annotations.getClass().equals(CustomTransactional.class)) {
                continue;
            }

            log.info("tx start!");

            method.invoke(targetObject, null);

            Thread.sleep(1000);
            log.info("tx end!");
        }
        return null;
    }
}
