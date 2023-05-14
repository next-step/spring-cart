package cart.web.cart;

import cart.domain.cart.Cart;
import cart.infrastructure.dao.CartDao;
import cart.web.cart.dto.CartAddRequestDto;
import io.restassured.RestAssured;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Sql(scripts = "classpath:schema.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CartDao cartDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;

        // reset tables
        jdbcTemplate.update("DELETE FROM cart");
        jdbcTemplate.update("DELETE FROM product");
        jdbcTemplate.update("DELETE FROM users");

        // user insert
        jdbcTemplate.update("INSERT INTO users(email, password) VALUES('a@a.com', 'passwordA')"); // id = 1
        jdbcTemplate.update("INSERT INTO users(email, password) VALUES('b@b.com', 'passwordB')"); // id = 2

        // product insert
        jdbcTemplate.update("INSERT INTO product(name, image_url, price) VALUES('상품A', 'image.com/imageA', 10000)"); // id = 1
        jdbcTemplate.update("INSERT INTO product(name, image_url, price) VALUES('상품B', 'image.com/imageB', 20000)"); // id = 2
        jdbcTemplate.update("INSERT INTO product(name, image_url, price) VALUES('상품C', 'image.com/imageC', 30000)"); // id = 3

        // cart insert
        jdbcTemplate.update("INSERT INTO cart(user_id, product_id) VALUES(1, 1)");
        jdbcTemplate.update("INSERT INTO cart(user_id, product_id) VALUES(1, 2)");
    }

    @Test
    void findAll() {
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", authorizationHeader("a@a.com", "passwordA"))
                .when().get("/api/v1/carts")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", is(2))
                .body("[0].name", is("상품A"))
                .body("[1].name", is("상품B"));
    }

    @Test
    void add() {
        // given
        CartAddRequestDto requestDto = new CartAddRequestDto(3L);

        // when
        Long addedCartId = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", authorizationHeader("a@a.com", "passwordA"))
                .body(requestDto)
                .when().post("/api/v1/carts")

        // then
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value()).extract().as(Long.class);

        Cart foundCart = assertDoesNotThrow(() -> cartDao.findById(addedCartId).get());
        assertThat(foundCart.getProductId()).isEqualTo(3L);
    }

    @Test
    void delete_valid() {
        // given, when
        Long deletedCartId = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", authorizationHeader("a@a.com", "passwordA"))
                .when().delete("/api/v1/carts/1")

        // then
                .then()
                .statusCode(HttpStatus.OK.value()).extract().as(Long.class);

        assertThat(cartDao.findById(deletedCartId)).isEmpty();
    }

    // TODO 예외 처리 로직 구현 후 예외 케이스 테스트
//    @Test
//    void delete_invalid_access_denied() {
//        // given, when
//        Long deletedCartId = RestAssured.given()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .header("Authorization", authorizationHeader("b@b.com", "passwordB"))
//                .when().delete("/api/v1/carts/1")
//
//                // then
//                .then()
//                .statusCode(HttpStatus.OK.value()).extract().as(Long.class);
//
//        assertThat(cartDao.findById(deletedCartId)).isEmpty();
//    }

    private String authorizationHeader(String email, String password) {
        String credential = new String(Base64.encodeBase64((email + ":" + password).getBytes()));
        return "BASIC " + credential;
    }

}
