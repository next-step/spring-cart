package cart.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cart.domain.Cart;

@SpringBootTest
public class CartServiceTest {
    @Autowired
    private CartService cartService;

    @Test
    public void getListTest() {
        List<Cart> carts = cartService.getList();
        carts.forEach(cart -> System.out.println(cart));
    }
}
