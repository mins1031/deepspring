package hello.advanced.custom_tx;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Proxy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class orderServiceImplTest {

    @Test
    void JDKDynamicProxy로_커스텀TX_어노테이션이_동작한다() {
        OrderService orderServiceImpl = new OrderServiceImpl();
        CustomTxInvocationHandler customTxInvocationHandler = new CustomTxInvocationHandler(orderServiceImpl);
        OrderService orderService = (OrderService) Proxy.newProxyInstance(OrderService.class.getClassLoader(), new Class[]{OrderService.class}, customTxInvocationHandler);
        orderService.save();
        orderService.update();
        orderService.get();
        orderService.error();
    }

    @Test
    public void CGLIB으로_커스텀TX_어노테이션이_동작한다() {
        ConcreteOrderService orderServiceImpl = new ConcreteOrderService();

        CustomTxMethodInterceptor customTxMethodInterceptor = new CustomTxMethodInterceptor(orderServiceImpl);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteOrderService.class);
        enhancer.setCallback(customTxMethodInterceptor);

        ConcreteOrderService concreteOrderService = (ConcreteOrderService) enhancer.create();
        concreteOrderService.save();
        concreteOrderService.get();
        concreteOrderService.update();
        concreteOrderService.error();
    }
}
