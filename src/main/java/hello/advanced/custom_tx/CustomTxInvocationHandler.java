package hello.advanced.custom_tx;

import lombok.extern.slf4j.Slf4j;

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
        CustomTransactional customTransactional = method.getDeclaredAnnotation(CustomTransactional.class);
        Object invoke = null;
        if (customTransactional == null) {
            method.invoke(targetObject, args);
        }

        if (customTransactional != null) {
            log.info("start transaction");
            try {
                invoke = method.invoke(targetObject, args);
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
