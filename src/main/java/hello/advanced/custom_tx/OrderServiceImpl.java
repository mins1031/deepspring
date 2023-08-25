package hello.advanced.custom_tx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order get() {
        log.info("get!");
        return new Order("get Order");
    }

    @Override
    @CustomTransactional
    public void save() {
        log.info("service save!");

    }

    @Override
    @CustomTransactional
    public void update() {
        log.info("service update!");
    }
}
