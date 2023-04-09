package cart.product.api;

import cart.RestAssuredApiSteps;
import cart.admin.domain.Product;
import cart.product.dao.ProductCartDao;
import cart.product.domain.Cart;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductCartContollerTest {

    @LocalServerPort
    private int port;

    private String base64;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductCartDao productCartDao;

    List<Cart> carts;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        this.base64 = Base64.encodeBase64String("test@1:password1".getBytes());
        jdbcTemplate.execute("delete from cart_list");

        carts = Arrays.asList(
                new Cart(1, "test@1", 1)
                , new Cart(2, "test@2", 2)
        );
    }

    @DisplayName("장바구니에 상품 추가 API 테스트")
    @Test
    void addCart() {

        var result = given()
                .header("Authorization", "Basic " + base64)
                .pathParam("productId", 1)
                .when()
                .post("/carts/{productId}")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.ACCEPTED.value());

    }

    @DisplayName("장바구니에 상품 제거 API 테스트")
    @Test
    void deleteCart() {

        Integer cartId = productCartDao.insertProduct(carts.get(0));

        var result = given()
                .header("Authorization", "Basic " + base64)
                .pathParam("cartId", cartId)
                .when()
                .delete("/carts/{cartId}")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.ACCEPTED.value());

    }

    @DisplayName("장바구니 조회 테스트")
    @Test
    void selectCarts() {

        carts.stream().forEach(cart -> productCartDao.insertProduct(cart));

        var result = given()
                .header("Authorization", "Basic " + base64)
                .when()
                .get("/carts/list")
                .then()
                .extract();

        assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());

    }
}
