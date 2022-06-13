package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    public void name() {
        logic1();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        log.info("비즈니스로직1 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        log.info("비즈니스로직2 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime={}", resultTime);
    }

    /**
     * 템플릿 메서드 패턴 적용
     * */
    @Test
    public void templateMethodV1() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    //기존 탬플릿 메서드 패턴은 다른 조건이 생길때마다 계속 클래스를 생성해 줘야 했다. 이러한 단점을 개선할수 있는것이 익명 내부 클래스로 구현하는 것이다.
    // 다만 이건 가독성 측면에서 좋은 코드는 아닌거 같아서 클래스가 5개 이상까지 클어나면 해당 방식으로 하는게 좋을거 같기도 하다
    @Test
    public void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("익명 내부 클래스로 구현한 call 메서드1");
            }
        };
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("익명 내부 클래스로 구현한 call 메서드2");
            }
        };
        template2.execute();
    }
}
