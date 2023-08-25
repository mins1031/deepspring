package hello.advanced.custom_tx;

import hello.advanced.proxy.jdkdynamic.code.AInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Proxy;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class orderServiceImplTest {

    @Test
    void SAVE는_커스텀TX_어노테이션이_동작한다() {
        OrderService orderServiceImpl = new OrderServiceImpl();
        CustomTxInvocationHandler customTxInvocationHandler = new CustomTxInvocationHandler(orderServiceImpl);
        OrderService orderService = (OrderService) Proxy.newProxyInstance(OrderService.class.getClassLoader(), new Class[]{OrderService.class}, customTxInvocationHandler);
        orderService.save();

        orderService.update();

        orderService.get();
    }
}