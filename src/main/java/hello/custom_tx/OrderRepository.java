package hello.custom_tx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class OrderRepository {
    private Map<String, Order> orderMap = new HashMap();

    public void save(String orderId, Order order) {
        orderMap.put(orderId, order);
        log.info("repo save!");
    }
}
