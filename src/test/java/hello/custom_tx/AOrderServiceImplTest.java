package hello.custom_tx;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = {"hello.custom_tx"})
class AOrderServiceImplTest {
    @Autowired
    private AOrderService AOrderService;

    @Test
    void SAVE는_커스텀TX_어노테이션이_동작한다() {
        System.out.println("d");
    }
}