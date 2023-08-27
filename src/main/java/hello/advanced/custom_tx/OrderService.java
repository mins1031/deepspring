package hello.advanced.custom_tx;


public interface OrderService {
    Order get();

    @CustomTransactional
    void save();

    @CustomTransactional
    void update();

    @CustomTransactional
    void error();
}
