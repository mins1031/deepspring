package hello.advanced.custom_tx;

import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class TxMain {

    public static void main(String[] args) {
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        CustomTxInvocationHandler customTxInvocationHandler = new CustomTxInvocationHandler(orderServiceImpl);
        OrderService orderService = (OrderService) Proxy.newProxyInstance(OrderService.class.getClassLoader(), new Class[]{OrderService.class}, customTxInvocationHandler);
        orderService.save();
        orderService.update();
        orderService.get();

        ConcreteOrderService concreteOrderService = new ConcreteOrderService();
        CustomTxMethodInterceptor customTxMethodInterceptor = new CustomTxMethodInterceptor(concreteOrderService);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteOrderService.class);
        enhancer.setCallback(customTxMethodInterceptor);

        ConcreteOrderService proxyingConcreteOrderService = (ConcreteOrderService) enhancer.create();
        proxyingConcreteOrderService.save();
        proxyingConcreteOrderService.get();
        proxyingConcreteOrderService.update();
        proxyingConcreteOrderService.throwException();
    }
}
