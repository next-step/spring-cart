package cart.repository;

import cart.domain.Cart;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class CartRepositoryTest {

    private final static long MEMBER1_ID = 1;
    private final static long MEMBER2_ID = 2;
    @Autowired
    private CartRepository cartRepository;

    @BeforeEach
    void before() {

        cartRepository.save(new Cart(1L, MEMBER1_ID, 1));
        cartRepository.save(new Cart(2L, MEMBER1_ID, 2));
        cartRepository.save(new Cart(3L, MEMBER2_ID, 2));
    }

    @AfterEach
    void after() {
        cartRepository.deleteAll(MEMBER1_ID);
        cartRepository.deleteAll(MEMBER2_ID);
    }

    @Test
    public void saveAndFindAllGroupByUser() {
        List<Cart> allCartProductsByMember1 = cartRepository.findAll(MEMBER1_ID);
        List<Cart> allCartProductsByMember2 = cartRepository.findAll(MEMBER2_ID);


        List<Cart> expectListMember1 = List.of(
                new Cart(1L, MEMBER1_ID, 1),
                new Cart(2L, MEMBER1_ID, 2));

        List<Cart> expectListMember2 = List.of(new Cart(3L, MEMBER2_ID, 2));


        Assertions.assertThat(allCartProductsByMember1).containsExactly(expectListMember1.toArray(Cart[]::new));
        Assertions.assertThat(allCartProductsByMember2).containsExactly(expectListMember2.toArray(Cart[]::new));
    }

    @Test
    public void delete() {
        cartRepository.deleteById(MEMBER1_ID, 2);

        List<Cart> allCartProductsByMember1 = cartRepository.findAll(MEMBER1_ID);
        List<Cart> allCartProductsByMember2 = cartRepository.findAll(MEMBER2_ID);

        List<Cart> expectListMember1 = List.of(new Cart(1L, MEMBER1_ID, 1));
        List<Cart> expectListMember2 = List.of(new Cart(3L, MEMBER2_ID, 2));

        Assertions.assertThat(allCartProductsByMember1).containsExactly(expectListMember1.toArray(Cart[]::new));
        Assertions.assertThat(allCartProductsByMember2).containsExactly(expectListMember2.toArray(Cart[]::new));
    }
}