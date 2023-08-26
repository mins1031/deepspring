package hello.advanced.custom_tx;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteOrderService {

    public Order get() {
        log.info("get!");
        return new Order("get Order");
    }

    @CustomTransactional
    public void save() {
        log.info("service save!");

    }

    @CustomTransactional
    public void update() {
        log.info("service update!");
    }

    @CustomTransactional
    public void throwException() {
        log.info("throw exception!");
        throw new RuntimeException();
    }
}
