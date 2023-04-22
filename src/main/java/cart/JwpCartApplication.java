package cart;

import cart.domain.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootApplication
public class JwpCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwpCartApplication.class, args);
    }

}

@Component
class Dummy {

    public Dummy(Members members, Carts carts, Products products) {
        var savedMember1 = members.add(new Member("jinhwan@gmail.com", "1234"));
        var savedMember2 = members.add(new Member("ecsimsw@gmail.com", "1234"));
        var savedMember3 = members.add(new Member("nick.kim@gmail.com", "1234"));

        var savedProduct1 = products.add(new Product("A", "images/sample.jpeg", 10000));
        var savedProduct2 = products.add(new Product("B", "images/sample.jpeg", 20000));
        var savedProduct3 = products.add(new Product("C", "images/sample.jpeg", 30000));

        carts.add(new Cart(savedMember1, savedProduct1));
        carts.add(new Cart(savedMember1, savedProduct2));
        carts.add(new Cart(savedMember2, savedProduct1));
        carts.add(new Cart(savedMember3, savedProduct1));
        carts.add(new Cart(savedMember1, savedProduct3));
    }
}
