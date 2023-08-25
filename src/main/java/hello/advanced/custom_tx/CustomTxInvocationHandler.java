package hello.advanced.custom_tx;

import lombok.extern.slf4j.Slf4j;

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
        Object invoke = null;
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        log.info("tx start!");
        for (Annotation annotation : annotations) {
            if (!annotation.getClass().getName().contains("CustomTransactional")) {
                continue;
            }



            log.info("tx start!");
            log.info("database transaction start");
            try {
                if (method.getReturnType().equals(void.class)) {
                    method.invoke(targetObject, args);
                }

                //리턴타입 적용은 조금더 찾아볼것.
//                Class<?> returnType = method.getReturnType();
//                returnType.getName();

                invoke = method.invoke(targetObject, args);
            } catch (Exception e) {
                log.info("rollback!");
                log.info("database transaction rollback");
                throw e;
            }

            Thread.sleep(1000);
            log.info("tx end!");
            log.info("database transaction commit");
        }

        return invoke;
    }
}
