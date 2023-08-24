package hello.custom_tx;


import org.springframework.stereotype.Service;

public interface AOrderService {
    Order get();

    void save();

    void update();
}
