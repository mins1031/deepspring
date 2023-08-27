package hello.advanced.custom_tx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
public class OrderServiceImpl implements OrderService {

    @Override
    public Order get() {
        log.info("get!");
        return new Order("get Order");
    }

    @Override
    public void save() {
        log.info("service save!");

    }

    @Override
    public void update() {
        log.info("service update!");
    }

    @Override
    public void error() {
        log.info("error!");
        throw new RuntimeException();
    }
}
