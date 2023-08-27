package hello.aop.order;

import org.springframework.stereotype.Service;

@Service
public class BookInterfaceImpl implements BookInterface{

    public void save() {
        System.out.println("test");
    }
}
