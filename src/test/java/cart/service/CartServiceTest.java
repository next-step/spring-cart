package cart.service;

import cart.controller.response.CartResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class CartServiceTest {

    private final static long MEMBER1_ID = 1;

    @Autowired
    private CartService cartService;

    @Test
    void saveAndFindAll() {
        cartService.save(MEMBER1_ID, 1L);
        CartResponse expect = new CartResponse(1, "단짠단짠 피자", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQS8d0Z2B1npEessiAoXj1HvBea-p8FptPwow&usqp=CAU", 25000);
        List<CartResponse> allCart = cartService.findAll(MEMBER1_ID);

        Assertions.assertThat(allCart).contains(expect);
    }

    @Test
    void saveIllegalProduct() {
        Assertions.assertThatThrownBy(() -> cartService.save(MEMBER1_ID, -99L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상품이 존재하지 않습니다.");
    }

    @Test
    void deleteById() {
        cartService.save(MEMBER1_ID, 1L);
        cartService.deleteById(MEMBER1_ID, 1L);
        List<CartResponse> allCart = cartService.findAll(MEMBER1_ID);

        CartResponse expect = new CartResponse(1, "단짠단짠 피자", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQS8d0Z2B1npEessiAoXj1HvBea-p8FptPwow&usqp=CAU", 25000);

        Assertions.assertThat(allCart).doesNotContain(expect);
    }
}