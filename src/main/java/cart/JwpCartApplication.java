package cart;

import cart.domain.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class JwpCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwpCartApplication.class, args);
    }

}

@Component
class TestDummy {

    private final Members members;
    private final Carts carts;
    private final Products products;

    public TestDummy(Members members, Carts carts, Products products) {
        this.members = members;
        this.carts = carts;
        this.products = products;

        var savedMember1 = members.add(new Member("hi", "1234"));
        var savedMember2 = members.add(new Member("bye", "1234"));

        var savedProduct1 = products.add(new Product("A", "images/sample.jpeg", 123));
        var savedProduct2 = products.add(new Product("B", "images/sample.jpeg", 123));

        carts.add(new Cart(savedMember1, savedProduct1));
        carts.add(new Cart(savedMember1, savedProduct2));
        carts.add(new Cart(savedMember2, savedProduct1));
    }
}
