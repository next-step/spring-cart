package cart.infrastructure.dao;

import cart.domain.cart.Cart;
import cart.domain.product.Product;
import cart.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Sql(scripts = "classpath:schema.sql")
@JdbcTest
class CartDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    private CartDao cartDao;
    private UsersDao usersDao;
    private ProductDao productDao;

    @BeforeEach
    void setUp() {
        cartDao = new CartDao(jdbcTemplate, dataSource);
        usersDao = new UsersDao(jdbcTemplate, dataSource);
        productDao = new ProductDao(jdbcTemplate, dataSource);
    }

    @Test
    void insert() {
        // given
        User insertedUser = insertUser("a@a.com", "passwordA");
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);

        // when
        Cart insertedCart = insertCart(insertedUser.getId(), insertedProduct.getId());

        // then
        assertThat(insertedCart.getId()).isNotNull();
    }

    @Test
    void findById() {
        // given
        User insertedUser = insertUser("a@a.com", "passwordA");
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);
        Cart insertedCart = insertCart(insertedUser.getId(), insertedProduct.getId());

        // when
        Cart foundCart = assertDoesNotThrow(() -> cartDao.findById(insertedCart.getId()).get());

        // then
        assertThat(foundCart.getUserId()).isEqualTo(insertedCart.getUserId());
        assertThat(foundCart.getProductId()).isEqualTo(insertedCart.getProductId());
    }

    @Test
    void findAllByUserId() {
        // given
        User insertedUser = insertUser("a@a.com", "passwordA");
        User anotherUser = insertUser("b@b.com", "passwordB");

        Product insertedProduct1 = insertProduct("상품A", "image.com/imageA", 10000);
        Product insertedProduct2 = insertProduct("상품B", "image.com/imageB", 20000);

        insertCart(insertedUser.getId(), insertedProduct1.getId());
        insertCart(insertedUser.getId(), insertedProduct2.getId());

        // when
        List<Cart> carts = cartDao.findAllByUserId(insertedUser.getId());

        // then
        assertThat(carts).flatExtracting(Cart::getProductId)
                .containsExactly(insertedProduct1.getId(), insertedProduct2.getId());

        assertThat(cartDao.findAllByUserId(anotherUser.getId())).hasSize(0);
    }

    @Test
    void findAllByProductId() {
        // given
        User insertedUser1 = insertUser("a@a.com", "passwordA");
        User insertedUser2 = insertUser("b@b.com", "passwordB");

        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);

        insertCart(insertedUser1.getId(), insertedProduct.getId());
        insertCart(insertedUser2.getId(), insertedProduct.getId());

        // when
        List<Cart> carts = cartDao.findAllByProductId(insertedProduct.getId());

        // then
        assertThat(carts).flatExtracting(Cart::getUserId)
                .containsExactly(insertedUser1.getId(), insertedUser2.getId());
    }

    @Test
    void delete() {
        // given
        User insertedUser = insertUser("a@a.com", "passwordA");
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);
        Cart insertedCart = insertCart(insertedUser.getId(), insertedProduct.getId());

        // when
        cartDao.delete(insertedCart.getId());

        // then
        assertThatThrownBy(() -> cartDao.findById(insertedCart.getId()).get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void batchDelete() {
        // given
        User insertedUser1 = insertUser("a@a.com", "passwordA");
        User insertedUser2 = insertUser("b@b.com", "passwordB");
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);
        Cart insertedCart1 = insertCart(insertedUser1.getId(), insertedProduct.getId());
        Cart insertedCart2 = insertCart(insertedUser2.getId(), insertedProduct.getId());

        // when
        cartDao.batchDelete(List.of(insertedCart1.getId(), insertedCart2.getId()));

        // then
        assertThatThrownBy(() -> cartDao.findById(insertedCart1.getId()).get())
                .isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() -> cartDao.findById(insertedCart2.getId()).get())
                .isInstanceOf(NoSuchElementException.class);
    }

    private Cart insertCart(Long userId, Long productId) {
        Cart givenCart = Cart.builder()
                .userId(userId)
                .productId(productId)
                .build();

        return cartDao.insert(givenCart);
    }

    private User insertUser(String email, String password) {
        User givenUser = User.builder()
                .email(email)
                .password(password)
                .build();

        return usersDao.insert(givenUser);
    }

    private Product insertProduct(String name, String imageUrl, int price) {
        Product givenProduct = Product.builder()
                .name(name)
                .imageUrl(imageUrl)
                .price(price)
                .build();

        return productDao.insert(givenProduct);
    }
}
