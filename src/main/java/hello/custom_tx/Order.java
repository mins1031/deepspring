package hello.custom_tx;

import lombok.Getter;

@Getter
public class Order {
    private String name;

    public Order(String name) {
        this.name = name;
    }
}
