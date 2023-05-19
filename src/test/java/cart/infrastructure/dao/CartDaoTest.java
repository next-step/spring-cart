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

    private ProductDao productDao;
    private UsersDao usersDao;
    private CartDao cartDao;

    @BeforeEach
    void setUp() {
        productDao = new ProductDao(jdbcTemplate, dataSource);
        usersDao = new UsersDao(jdbcTemplate, dataSource);
        cartDao = new CartDao(jdbcTemplate, dataSource, productDao, usersDao);
    }

    @Test
    void cart_테이블에_장바구니_데이터를_삽입한다() {
        // given
        User insertedUser = insertUser("a@a.com", "passwordA");
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);

        // when
        Cart insertedCart = insertCart(insertedUser, insertedProduct);

        // then
        assertThat(insertedCart.getId()).isNotNull();
    }

    @Test
    void cart_테이블에서_id로_장바구니_데이터를_찾는다() {
        // given
        User insertedUser = insertUser("a@a.com", "passwordA");
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);
        Cart insertedCart = insertCart(insertedUser, insertedProduct);

        // when
        Cart foundCart = assertDoesNotThrow(() -> cartDao.findById(insertedCart.getId()).get());

        // then
        assertThat(foundCart.getUser()).isEqualTo(insertedCart.getUser());
        assertThat(foundCart.getProduct()).isEqualTo(insertedCart.getProduct());
    }

    @Test
    void cart_테이블에서_userId로_모든_장바구니_데이터를_찾는다() {
        // given
        User insertedUser = insertUser("a@a.com", "passwordA");
        User anotherUser = insertUser("b@b.com", "passwordB");

        Product insertedProduct1 = insertProduct("상품A", "image.com/imageA", 10000);
        Product insertedProduct2 = insertProduct("상품B", "image.com/imageB", 20000);

        insertCart(insertedUser, insertedProduct1);
        insertCart(insertedUser, insertedProduct2);

        // when
        List<Cart> carts = cartDao.findAllByUserId(insertedUser.getId());

        // then
        assertThat(carts).flatExtracting(Cart::getProduct)
                .containsExactly(insertedProduct1, insertedProduct2);

        assertThat(cartDao.findAllByUserId(anotherUser.getId())).hasSize(0);
    }

    @Test
    void cart_테이블에서_productId로_모든_장바구니_데이터를_찾는다() {
        // given
        User insertedUser1 = insertUser("a@a.com", "passwordA");
        User insertedUser2 = insertUser("b@b.com", "passwordB");

        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);

        insertCart(insertedUser1, insertedProduct);
        insertCart(insertedUser2, insertedProduct);

        // when
        List<Cart> carts = cartDao.findAllByProductId(insertedProduct.getId());

        // then
        assertThat(carts).flatExtracting(Cart::getUser)
                .containsExactly(insertedUser1, insertedUser2);
    }

    @Test
    void cart_테이블에서_하나의_장바구니_데이터를_삭제한다() {
        // given
        User insertedUser = insertUser("a@a.com", "passwordA");
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);
        Cart insertedCart = insertCart(insertedUser, insertedProduct);

        // when
        cartDao.delete(insertedCart.getId());

        // then
        assertThatThrownBy(() -> cartDao.findById(insertedCart.getId()).get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void cart_테이블에서_여러개의_장바구니_데이터를_삭제한다() {
        // given
        User insertedUser1 = insertUser("a@a.com", "passwordA");
        User insertedUser2 = insertUser("b@b.com", "passwordB");
        Product insertedProduct = insertProduct("상품A", "image.com/imageA", 10000);
        Cart insertedCart1 = insertCart(insertedUser1, insertedProduct);
        Cart insertedCart2 = insertCart(insertedUser2, insertedProduct);

        // when
        cartDao.batchDelete(List.of(insertedCart1.getId(), insertedCart2.getId()));

        // then
        assertThatThrownBy(() -> cartDao.findById(insertedCart1.getId()).get())
                .isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(() -> cartDao.findById(insertedCart2.getId()).get())
                .isInstanceOf(NoSuchElementException.class);
    }

    private Cart insertCart(User user, Product product) {
        Cart givenCart = Cart.builder()
                .user(user)
                .product(product)
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
