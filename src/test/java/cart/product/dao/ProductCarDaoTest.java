package cart.product.dao;

import cart.product.domain.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProductCarDaoTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductCartDao productCartDao;

    List<Cart> carts;

    @BeforeEach
    void setting() {
        jdbcTemplate.execute("delete from cart_list");
        carts = Arrays.asList(
                new Cart(1, "test@1", 1)
                , new Cart(2, "test@2", 2)
        );
    }

    @DisplayName("카트 정보 INSERT 테스트")
    @Test
    void cartInsertTest() {
        carts.stream().forEach(cart ->
                assertThat(productCartDao.insertProduct(cart)));
    }

    @DisplayName("장바구니에 담은 리스트 조회 하기")
    @Test
    void selectCartList() {
        carts.stream().forEach(cart ->
                assertThat(productCartDao.insertProduct(cart)));

        List<Cart> selectCarts = productCartDao.selectCarts("test@1");

        assertThat(selectCarts).hasSize(1);
    }

    @DisplayName("장바구니 담은 리스트 삭제하기")
    @Test
    void deleteCart() {

        int cartId = productCartDao.insertProduct(carts.get(0));

        productCartDao.deleteCart(cartId);

    }
}
