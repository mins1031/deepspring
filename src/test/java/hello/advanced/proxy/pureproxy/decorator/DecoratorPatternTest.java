package hello.advanced.proxy.pureproxy.decorator;

import hello.advanced.proxy.pureproxy.decorator.code.Component;
import hello.advanced.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.advanced.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.advanced.proxy.pureproxy.decorator.code.RealComponent;
import hello.advanced.proxy.pureproxy.decorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component component = new RealComponent();
        DecoratorPatternClient decoratorPatternClient = new DecoratorPatternClient(component);
        decoratorPatternClient.execute();
    }

    @Test
    void decorator1() {
        Component component = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(component);
        DecoratorPatternClient decoratorPatternClient = new DecoratorPatternClient(messageDecorator);
        decoratorPatternClient.execute();
    }

    @Test
    void decorator2() {
        Component component = new RealComponent();
        Component messageDecorator = new MessageDecorator(component);
        Component timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient decoratorPatternClient = new DecoratorPatternClient(timeDecorator);
        decoratorPatternClient.execute();
    }
}
