package hello.advanced.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator extends Decorator implements Component {

    public MessageDecorator(Component component) {
        super(component);
    }

    @Override
    public String operation() {
        log.info("MessageDecorator 실행");

        //응답값 꾸며줌.
        String result = component.operation();
        String decoResult = "*****" + result + "*****";
        log.info("MessageDecorator 꾸미기 적용 전={}, 적용 후={}", result, decoResult);
        return decoResult;
    }
}
