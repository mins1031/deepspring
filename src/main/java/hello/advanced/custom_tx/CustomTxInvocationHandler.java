package hello.advanced.custom_tx;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
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
        CustomTransactional customTransactional = method.getDeclaredAnnotation(CustomTransactional.class);
        Object invoke = null;

        if (customTransactional == null) {
            method.invoke(targetObject, args);
        }

        if (customTransactional != null) {
            log.info("tx start!");
            log.info("database transaction start!");
            try {
                //리턴타입 적용은 조금더 찾아볼것.
//                if (method.getReturnType().equals(void.class)) {
//                    method.invoke(targetObject, args);
//                }
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
