package hello.advanced.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator extends Decorator implements Component {

    public TimeDecorator(Component component) {
        super(component);
    }

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");
        long start = System.currentTimeMillis();

        String result = component.operation();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - start;
        log.info("TimeDecorator 종료 resultTime={}ms", resultTime);
        return result;
    }
}
